package jp.funx.tekapo;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import jp.funx.tekapo.api.Api;
import jp.funx.tekapo.api.Message;
import jp.funx.tekapo.api.Content;
import jp.funx.tekapo.api.Problem;
import jp.funx.tekapo.api.QuizQuestions;
import jp.funx.tekapo.api.Request;
import jp.funx.tekapo.question.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
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

public class MainActivity extends FragmentActivity {
	Button start;
	Button filter;
	ProgressBar progressBar;
	Question q;
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
		start = findViewById(R.id.home_start);
		start.setVisibility(View.GONE); // not using this button in Tekapo
		filter = findViewById(R.id.home_filter);
		filter.setVisibility(View.GONE); // not using this button in Tekapo
		progressBar = findViewById(R.id.progressBar2);
		start.setOnClickListener(onClickListener);
		filter.setOnClickListener(onClickListener);
		progressBar.setVisibility(View.VISIBLE);
		start.setClickable(false);
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
		boolean isJapanese = rnd.nextInt(4) >= 3 ? true : false;
//		boolean isMultichoice = true; //rnd.nextInt(5) >= 2 ? true : false; // TODO: handle true/false responses later
		int numProbs = 10;
		ArrayList<String> topics = new ArrayList<String>();
//		for (int i = 0; i < numProbs; i++) {
//			switch (rnd.nextInt(12)) {

				// TODO: topics are hardcoded in Content.java model class for now so ensure they're in sync. Make 'em dynamic one day.
//				case 0:
					topics.add("science");
//					break;
//				case 1:
					topics.add("computers");
//					break;
//				case 2:
					topics.add("nature");
//					break;
//				case 3:
					topics.add("famous people");
//					break;
//				case 4:
					topics.add("video games");
//					break;
//				case 5:
					topics.add("anime and manga");
//					break;
//				case 6:
					topics.add("technology");
//					break;
//				case 7:
//					topics.add("programming");
//					break;
//
//				default:
//				case 8:
//				case 9:
//				case 10:
//				case 11:
					topics.add("math");
//					break;
//			}
//		}

		// e.g. Give 5 multiple choice questions suitable for a 10 year old boy on a mix of topics in space, technology, math, science, programming and history.
		// All questions and answers must be in the Japanese language and in JSON format.
		String problem = "Give " + numProbs + " multiple choice questions suitable for a 12 year old on a mix of topics in "
				+ String.join(", ", topics) + ". " +
//				+ "Answers must be in " + (isMultichoice ? "4 multi-choice" : "true/false") + " format."
				"All questions and answers must be in the " + (isJapanese ? "Japanese" : "English") + " language and in JSON format.";
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
				"Bearer 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",
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

					if (response.errorBody() != null) {
						BufferedReader reader = null;
						StringBuilder sb = new StringBuilder();
						try {
							reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
							String line;
							while ((line = reader.readLine()) != null) {
								sb.append(line);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						String finallyError = sb.toString();
						Toast.makeText(getApplicationContext(),  finallyError, Toast.LENGTH_LONG).show();
						Thread.sleep(20000); // avoid API call threshold limits
						fetchQuestionAPI();
						return;
					}

					contentStr = quizQuestions.getChoices().get(0).getMessage().getContent();
					contentStr = contentStr.replaceAll("\n", "");
					contentStr = contentStr.replaceAll("\t", "");
					Log.v("content", contentStr);

					if (contentStr.toLowerCase().contains("as an ai language model")
							|| contentStr.toLowerCase().contains("inaccurate content")
							|| contentStr.toLowerCase().contains("inappropriate content")) {
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

					List<Problem> problems;
					GsonBuilder gsonBuilder = new GsonBuilder();
					gsonBuilder.setLenient();
					Gson gson = gsonBuilder.create();

					try {
						// Try first if the resonse is JSON [] array of Problem.class objects
						Type userListType = new TypeToken<ArrayList<Problem>>() {}.getType();
						problems = gson.fromJson(contentStr, userListType);
					} catch (Exception e) {
						content = gson.fromJson(contentStr, Content.class);
						problems = content.getProblems();
					}
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
				startQuiz();
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

	private void startQuiz() {
		Intent intent = new Intent(MainActivity.this, QuizActivity.class);
		intent.putExtra("question", q);
		startActivity(intent);
		finish();
	}

	final String TAG = "MainActivity";

	Service mService;
	boolean mBound = false;

	/** Defines callbacks for service binding, passed to bindService() */
	private final ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className,
									   IBinder service) {
			// We've bound to LocalService, cast the IBinder and get LocalService instance
			Service.TvLockBinder binder = (Service.TvLockBinder) service;
			mService = binder.getService();
			Log.d(TAG,"onServiceConnected() - bound to service");
			mBound = true;

			// Test with dummy questions
			q = new Question(getApplicationContext());
			// SOF PART 1
//			q.question.add("What is the correct answer (test question @ " + new java.util.Date().toGMTString() + ")?");
//			q.optA.add("Answer 1 [correct one]");
//			q.optB.add("Answer 2");
//			q.optC.add("Answer 3");
//			q.optD.add("Answer 4");
//			q.Answer.add(0);
//			q.question.add("What is the correct answer (test question)?");
//			q.optA.add("Answer 1");
//			q.optB.add("Answer 2");
//			q.optC.add("Answer 3");
//			q.optD.add("Answer 4 [correct one]");
//			q.Answer.add(3);
//			q.question.add("What is the correct answer (test question)?");
//			q.optA.add("Answer 1");
//			q.optB.add("Answer 2");
//			q.optC.add("Answer 3 [correct one]");
//			q.optD.add("Answer 4");
//			q.Answer.add(2);
			// EOF PART 1
			// SOF PART 2
//			String contentStr = "[  {    \"question\": \"What is the process by which plants make food called?\",    \"options\": [\"Photosynthesis\", \"Cell respiration\", \"Mitosis\", \"Meiosis\"],    \"answer\": \"Photosynthesis\"  },  {    \"question\": \"Which of the following is not a type of computer?\",    \"options\": [\"Laptop\", \"Desktop\", \"Tablet\", \"Radio\"],    \"answer\": \"Radio\"  },  {    \"question\": \"What animal is known for its hibernation in the winter?\",    \"options\": [\"Polar bear\", \"Kangaroo\", \"Eagle\", \"Groundhog\"],    \"answer\": \"Groundhog\"  },  {    \"question\": \"Who is known for inventing the light bulb?\",    \"options\": [\"Thomas Edison\", \"Albert Einstein\", \"Nikola Tesla\", \"Leonardo da Vinci\"],    \"answer\": \"Thomas Edison\"  },  {    \"question\": \"Which game requires you to build and explore a world made up of blocks?\",    \"options\": [\"Minecraft\", \"Fortnite\", \"Roblox\", \"Overwatch\"],    \"answer\": \"Minecraft\"  },  {    \"question\": \"Which manga follows the story of a young boy who wants to become the King of Pirates?\",    \"options\": [\"One Piece\", \"Naruto\", \"Dragon Ball\", \"Death Note\"],    \"answer\": \"One Piece\"  },  {    \"question\": \"What is an example of wearable technology?\",    \"options\": [\"Smartwatch\", \"Smartphone\", \"Computer mouse\", \"Keyboard\"],    \"answer\": \"Smartwatch\"  },  {    \"question\": \"Which programming language is often used for building websites?\",    \"options\": [\"Java\", \"Python\", \"HTML\", \"CSS\"],    \"answer\": \"HTML\"  },  {    \"question\": \"What is the sum of 5 + 7?\",    \"options\": [\"8\", \"10\", \"12\", \"14\"],    \"answer\": \"12\"  },  {    \"question\": \"What is the branch of math that deals with the study of shapes and their properties?\",    \"options\": [\"Geometry\", \"Algebra\", \"Calculus\", \"Statistics\"],    \"answer\": \"Geometry\"  }]\n";
//			GsonBuilder gsonBuilder = new GsonBuilder();
//			gsonBuilder.setLenient();
//			Gson gson = gsonBuilder.create();
//			Type userListType = new TypeToken<ArrayList<Problem>>(){}.getType();
//			List<Problem> problems = gson.fromJson(contentStr, userListType);
//			Collections.shuffle(problems);
//			for (int i = 0; i < (problems.size() < 10 ? problems.size() : 10); i++) {
//				q.question.add(problems.get(i).getProblem());
//				q.optA.add(problems.get(i).getChoices().get(0));
//				q.optB.add(problems.get(i).getChoices().get(1));
//				q.optC.add(problems.get(i).getChoices().get(2));
//				q.optD.add(problems.get(i).getChoices().get(3));
//				q.Answer.add(problems.get(i).getAnswerIndex());
//			}
			// EOF PART 2
//			Log.d(TAG, "onServiceConnected() starting QuizActivity");
//			startQuiz();

			// Start the quiz immediately
			fetchQuestionAPI();
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			mBound = false;
		}
	};

	@Override
	public void onStart(){
		Log.d(TAG,"onStart()");
		super.onStart();
		if ( !isServiceRunning(Service.class, getApplicationContext()) ) {
			Log.d(TAG,"onStart() - startService()");
			MainActivity.startService(getApplicationContext());
//			Toast.makeText(this, "startService(intent)", Toast.LENGTH_SHORT).show();
		}
		else {
			Log.d(TAG,"onStart() - service already started");
		}
		if ( isServiceRunning(Service.class, getApplicationContext()) ) {
			if (!mBound) {
				Log.d(TAG,"onStart() - bindService()");
				// Bind to Service
				Intent intent = new Intent(this, Service.class);
				bindService(intent, connection, Context.BIND_AUTO_CREATE);
//				Toast.makeText(this, "bindService()", Toast.LENGTH_SHORT).show();
			} else {
				Log.d(TAG,"onStart() - already bound");
			}
		}
		else
		{
			Log.d(TAG,"onStart() - cannot bindService() - service not running");
			Toast.makeText(this, "cannot bindService() - service not running", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onDestroy(){
		Log.d(TAG,"onDestroy()");
		super.onDestroy();
		if (mBound){
			unbindService(connection);
			mBound = false;
		}
	}

	public static boolean isServiceRunning(Class<?> serviceClass, Context context) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (serviceClass.getName().equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}

	public static void startService(Context context) {
		Intent intent = new Intent(context, Service.class);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			context.startForegroundService(intent);
		} else {
			context.startService(intent);
		}
	}
}