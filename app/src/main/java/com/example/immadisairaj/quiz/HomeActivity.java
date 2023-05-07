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
import com.example.immadisairaj.quiz.api.QnA;
import com.example.immadisairaj.quiz.api.QuizQuestions;
import com.example.immadisairaj.quiz.api.Request;
import com.example.immadisairaj.quiz.api.Result;
import com.example.immadisairaj.quiz.question.Question;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Random rnd = new Random();
		boolean isJapanese = false;// rnd.nextInt(6) >= 5 ? true : false;　// TODO: handle Japanese responses later
		boolean isMultichoice = true; //rnd.nextInt(5) >= 2 ? true : false; // TODO: handle true/false responses later
		String topic;
		switch (rnd.nextInt(12)) {
			case 0: topic = "science"; break;
			case 1: topic = "computers"; break;
			case 2: topic = "nature"; break;
			case 3: topic = "famous people"; break;
			case 4: topic = "video games"; break;
			case 5: topic = "anime and manga"; break;
			case 6: topic = "technology"; break;
			case 7: topic = "programming"; break;

			default:
			case 8:
			case 9:
			case 10:
			case 11:
				topic = "math"; break;
		}

		Message problem = new Message("user", "Give a problem and the answer on the topic of "
				+ topic
				+ ", in " + (isMultichoice ? "multi-choice" : "true/false") + " format"
				+ ", " + (isJapanese ? " suitable for a 10 year old child, and in the Japanese language at a 10 year old reading level" :
				"suitable for a kid aged between 5 and 9. Response must be in JSON format."));
		ArrayList<Message> messages = new ArrayList<>();
		messages.add(problem);
		Request request = new Request("gpt-3.5-turbo", messages);

		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder()
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

				QuizQuestions quizQuestions = response.body();
				String content = quizQuestions.getChoices().get(0).getMessage().getContent();
				content = content.replaceAll("\n", "");
				//Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
				Log.v("content", content);

				Gson gson = new Gson();
				QnA qNa = gson.fromJson(content, QnA.class);

				progressBar.setVisibility(View.INVISIBLE);
				start.setClickable(true);
				Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
				intent.putExtra("question", q);
				startActivity(intent);
			}

			@Override
			public void onFailure(Call<QuizQuestions> call, Throwable t) {
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