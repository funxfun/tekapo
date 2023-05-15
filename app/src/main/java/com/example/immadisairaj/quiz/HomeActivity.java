package com.example.immadisairaj.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.immadisairaj.quiz.api.Api;
import com.example.immadisairaj.quiz.api.Message;
import com.example.immadisairaj.quiz.api.Content;
import com.example.immadisairaj.quiz.api.Problem;
import com.example.immadisairaj.quiz.api.QuizQuestions;
import com.example.immadisairaj.quiz.api.Request;
import com.example.immadisairaj.quiz.api.Result;
import com.example.immadisairaj.quiz.question.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
	Button start;
	Button filter;
	ProgressBar progressBar;
	Question q;
	String difficulty;
	String category;
	View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			if (view.getId() == R.id.home_start) {
				progressBar.setVisibility(View.VISIBLE);
				q = new Question(getApplicationContext());
				view.setClickable(false);
				fetchQuestionAPI();
			} else if (view.getId() == R.id.home_filter) {
				Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		}
	};
//	int numberApiCalls = 0;
//	int numberApiErrs = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Testing ChatGPT-generated regex answer
		// https://twitter.com/WanderingIshiki/status/1655141350835367937?s=20
//		Pattern p = Pattern.compile("Problem:\\s+(.+)\\s+A.\\s+(.+)\\s+B.\\s+(.+)\\s+C.\\s+(.+)\\s+Answer:\\s+(.+)\\s+");
//		Matcher m = p.matcher("Problem: What should you do if you start feeling tired while playing video games?\n\nA. Keep playing until you win\nB. Take a break and rest your eyes and body\nC. Drink a lot of soda \n\nAnswer: B. Take a break and rest your eyes and body");
//		m.find();
//		Log.e("aeh", m.group(1));
//		Log.e("aeh", m.group(2));
//		Log.e("aeh", m.group(3));
//		Log.e("aeh", m.group(4));
//		Log.e("aeh", m.group(5));

		setContentView(R.layout.activity_home);
		setFilterDefaultValues();
		start = findViewById(R.id.home_start);
		filter = findViewById(R.id.home_filter);
		progressBar = findViewById(R.id.progressBar2);
		start.setOnClickListener(onClickListener);
		filter.setOnClickListener(onClickListener);

		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		category = sharedPrefs.getString(
				getString(R.string.category_key),
				getString(R.string.medium_value)
		);

		difficulty = sharedPrefs.getString(
				getString(R.string.difficulty_key),
				getString(R.string.medium_value)
		);
	}

	private void setFilterDefaultValues() {
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		difficulty = sharedPrefs.getString(
				getString(R.string.difficulty_key),
				null
		);
		category = sharedPrefs.getString(
				getString(R.string.category_key),
				null
		);
		if (difficulty == null) {
			sharedPrefs
					.edit()
					.putString(getString(R.string.difficulty_key), getString(R.string.easy_value))
					.apply();
		}
		if (category == null) {
			sharedPrefs
					.edit()
					.putString(getString(R.string.category_key), getString(R.string.any_category_value))
					.apply();
		}
	}

	public void fetchQuestionAPI() {
//		if (numberApiErrs > 3) {
//			numberApiCalls = 0;
//			numberApiErrs = 0;
//			Toast.makeText(getApplicationContext(), "Reached MAX number of API errors", Toast.LENGTH_LONG).show();
//			progressBar.setVisibility(View.INVISIBLE);
//			start.setClickable(true);
//			return;
//		}

		Random rnd = new Random();
		boolean isJapanese = false;// rnd.nextInt(6) >= 5 ? true : false;　// TODO: handle Japanese responses later
		boolean isMultichoice = true; //rnd.nextInt(5) >= 2 ? true : false; // TODO: handle true/false responses later
		int numProbs = 10;
		ArrayList<String> topics = new ArrayList<String>();
		for (int i = 0; i < numProbs; i++) {
			switch (rnd.nextInt(12)) {

				// TODO: topics are hardcoded in Content.java model class for now so ensure they're in sync. Make 'em dynamic one day.
				case 0:
					topics.add("science");
					break;
				case 1:
					topics.add("computers");
					break;
				case 2:
					topics.add("nature");
					break;
				case 3:
					topics.add("famous people");
					break;
				case 4:
					topics.add("video games");
					break;
				case 5:
					topics.add("anime and manga");
					break;
				case 6:
					topics.add("technology");
					break;
				case 7:
					topics.add("programming");
					break;

				default:
				case 8:
				case 9:
				case 10:
				case 11:
					topics.add("math");
					break;
			}
		}

		String problem = "Give " + numProbs + " educational problems and their answers on the topics of "
				+ String.join(", ", topics) + "."
				+ "Answers must be in " + (isMultichoice ? "4 multi-choice" : "true/false") + " format."
				+ " The content must be " +
				(isJapanese ? 	"suitable for a 12 year old boy, and in the Japanese language at a 12 year old reading level." :
						"suitable for a 12 year old boy.")
				+ " Responses must be in JSON format.";
		Log.v("problem", problem);

		Message message = new Message("user", problem);
		ArrayList<Message> messages = new ArrayList<>();
		messages.add(message);
		Request request = new Request("gpt-3.5-turbo", messages);

		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder()
				.connectTimeout(120, TimeUnit.SECONDS)
				.writeTimeout(120, TimeUnit.SECONDS)
				.readTimeout(120, TimeUnit.SECONDS)
				.addInterceptor(interceptor).build();

		Retrofit retrofit = new Retrofit.Builder()
                .client(client)
				.baseUrl(Api.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		Api api = retrofit.create(Api.class);
		Call<QuizQuestions> call = api.getQuizQuestions(
				"Bearer [API KEY]",
				"application/json",
				request
				);
		call.enqueue(new Callback<QuizQuestions>() {
			@Override
			public void onResponse(Call<QuizQuestions> call, Response<QuizQuestions> response) {

				Log.v("url-----", call.request().url().toString());

				String contentStr;
				Content content;
				try {
					QuizQuestions quizQuestions = response.body();
					contentStr = quizQuestions.getChoices().get(0).getMessage().getContent();
					contentStr = contentStr.replaceAll("\n", "");
					contentStr = contentStr.replaceAll("\t", "");
					Log.v("content", contentStr);

					if (contentStr.toLowerCase().contains("as an ai language model") || contentStr.contains("generate inappropriate content")) {
						// Sometimes we get silly responses like;
						// "As an AI language model I apologize as I cannot generate inappropriate or incorrect content, particularly for a 12 year old child."
						// or
						// "Sorry, as an AI language model I don't provide JSON format."
						// So force a retry
						Toast.makeText(getApplicationContext(), "Retrying coz of uncooperative AI model: " + contentStr, Toast.LENGTH_LONG).show();
						Thread.sleep(20000); // avoid API call threshold limits
						fetchQuestionAPI();
						return;
					}

					GsonBuilder gsonBuilder = new GsonBuilder();
					gsonBuilder.setLenient();
					Gson gson = gsonBuilder.create();
					content = gson.fromJson(contentStr, Content.class);

					List<Problem> problems = content.getProblems();
					if (problems == null || problems.size() == 0) {
						throw new IllegalStateException("Non-parseable problems response");
					}
					Collections.shuffle(problems);
					for (int i = 0; i < (problems.size() < numProbs ? problems.size() : numProbs); i++) {
						q.question.add(problems.get(i).getProblem());
						q.optA.add(problems.get(i).getChoices().get(0));
						q.optB.add(problems.get(i).getChoices().get(1));
						q.optC.add(problems.get(i).getChoices().get(2));
						q.optD.add(problems.get(i).getChoices().get(3));
						q.Answer.add(problems.get(i).getAnswerIndex());
					}
				} catch (Exception e) {
//					numberApiErrs++;
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Trying again. Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
					fetchQuestionAPI();
					return;
				}

//				if (numberApiCalls < 3) {
//					numberApiCalls++;
//					numberApiErrs = 0;
//					Toast.makeText(getApplicationContext(), "API call #" + (numberApiCalls + 1), Toast.LENGTH_SHORT).show();
//					fetchQuestionAPI();
//					return;
//				}

				progressBar.setVisibility(View.INVISIBLE);
				start.setClickable(true);
				Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
				intent.putExtra("question", q);
				startActivity(intent);
			}

			@Override
			public void onFailure(Call<QuizQuestions> call, Throwable t) {
//				numberApiErrs++;
//				fetchQuestionAPI();
				Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
				progressBar.setVisibility(View.INVISIBLE);
				start.setClickable(true);
			}
		});
	}

	void setOptions(Result r, int ran) {
//		List<String> wrong;
//		switch (ran) {
//			case 0:
//				try {
//					q.optA.add(java.net.URLDecoder.decode(r.getCorrectAnswer(), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//
//				wrong = r.getIncorrectAnswers();
//				try {
//					q.optB.add(java.net.URLDecoder.decode(wrong.get(0), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				// Options C, D are not applicable for Boolean Type Questions.
//				if (r.getType().equals("boolean")) {
//					q.optC.add("false");
//					q.optD.add("false");
//					return;
//				}
//				try {
//					q.optC.add(java.net.URLDecoder.decode(wrong.get(1), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				try {
//					q.optD.add(java.net.URLDecoder.decode(wrong.get(2), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				break;
//			case 1:
//				try {
//					q.optB.add(java.net.URLDecoder.decode(r.getCorrectAnswer(), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//
//				wrong = r.getIncorrectAnswers();
//				try {
//					q.optA.add(java.net.URLDecoder.decode(wrong.get(0), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				// Options C, D are not applicable for Boolean Type Questions.
//				if (r.getType().equals("boolean")) {
//					q.optC.add("false");
//					q.optD.add("false");
//					return;
//				}
//				try {
//					q.optC.add(java.net.URLDecoder.decode(wrong.get(1), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				try {
//					q.optD.add(java.net.URLDecoder.decode(wrong.get(2), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				break;
//			case 2:
//				try {
//					q.optC.add(java.net.URLDecoder.decode(r.getCorrectAnswer(), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//
//				wrong = r.getIncorrectAnswers();
//				try {
//					q.optA.add(java.net.URLDecoder.decode(wrong.get(0), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				try {
//					q.optB.add(java.net.URLDecoder.decode(wrong.get(1), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				try {
//					q.optD.add(java.net.URLDecoder.decode(wrong.get(2), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				break;
//			case 3:
//				try {
//					q.optD.add(java.net.URLDecoder.decode(r.getCorrectAnswer(), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//
//				wrong = r.getIncorrectAnswers();
//				try {
//					q.optA.add(java.net.URLDecoder.decode(wrong.get(0), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				try {
//					q.optB.add(java.net.URLDecoder.decode(wrong.get(1), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				try {
//					q.optC.add(java.net.URLDecoder.decode(wrong.get(2), "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				break;
//		}
	}
}