# Tekapo - Android TV GPT Quiz Unlock Screentime App for Kids

## About  Tekapo

[![Tekapo Demo]          // Title
((https://youtu.be/rJxNfyU83PM/0.jpg)] // Thumbnail
(https://youtu.be/rJxNfyU83PM "Tekapo Demo")    // Video Link

<a href="http://www.youtube.com/watch?feature=player_embedded&v=nTQUwghvy5Q" target="_blank">
 <img src="http://img.youtube.com/vi/nTQUwghvy5Q/mqdefault.jpg" alt="Watch the video" width="240" height="180" border="10" />
</a>

I have 3 young boys always coming up with a mirade of reasons to be allowed screen time on the TV. Sometimes it just gets too much trying to reason with them, or sometimes my wife and I are just too tired to try and engage them in rationale screen time conversation.

Tekapo was born as a result as an attempt to reduce that stress. Simply, I wanted an app that would control our Android TVs (any thus any apps like Youtube, Netflix, Disney+ etc.) such that they would be unlocked if they achieved certain tasks.

I then had the idea to prompt OpenAI's GPT LLM (large language model) for several multiple-choice questions covering a range of topics aimed our childrens' age.

This is a prototype app that I'd say is still in alpha. The responses from OpenAI tend to repeat the questions and the format is not consistent (hence the try-and-error attempt at implementing as many approaches as possible in Content.java).

### Running

1. Clone the repository.
1. Open the project in Android Studio and run it on your Android TV.

### Configuring (do this before running it on your TV)

1. Sign up to OpenAI's GPT API and get an API key. Change the <API key> after "Bearer" string in MainAcitivity.java:~181
1. Customize the code in MainActivity.java:110~158 to change things like the language (randomized between English and Japanese), the topics covered, and the age aimed for.

### APIs Used

[OpenAI GPT](https://platform.openai.com/docs/guides/gpt)

## Screenshots

<table>  
  <tr>  
    <td><img src="/screenshots/1.png" ></td>  
  </tr>  
  <tr> 
    <td><img src="/screenshots/2.png" ></td>  
  </tr>
  <tr> 
    <td><img src="/screenshots/3.png" ></td>  
  </tr>  
  <tr>  
    <td><img src="/screenshots/4.png" ></td>  
  </tr>
  <tr>  
    <td><img src="/screenshots/5.png" ></td>  
  </tr>  
  <tr> 
    <td><img src="/screenshots/6.png" ></td>  
  </tr>
  <tr> 
    <td><img src="/screenshots/7.png" ></td>  
  </tr>  
  <tr>  
    <td><img src="/screenshots/8.png" ></td>  
  </tr>
</table>  

## License

This project and its base (forked-from) projects are licensed under the MIT License, see the [LICENSE.md](https://github.com/funxfun/tekapo/blob/master/LICENSE) for more details.

## Credits

This project was built upon two projects to which we owe due credit. Thank you to all the contributors to the following projects for their efforts and contributing things open source.

* [Quiz](https://github.com/immadisairaj/Quiz/): An Android application which uses Open Trivia Api by Sai Rajendra Immadi et al.
* [TvLockO](https://github.com/humflbrumf/TvLockO): Child Lock for Android TV >= Oreo by Humfl Brumf