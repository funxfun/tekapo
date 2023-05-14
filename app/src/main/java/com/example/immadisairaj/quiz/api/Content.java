package com.example.immadisairaj.quiz.api;

import com.example.immadisairaj.quiz.question.Question;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Content {
     /**
      * !!TODO!!: Topics are hardcoded for now so ensure they're in sync. Make 'em dynamic one day.
      * !!TODO!!: Come up with a better way to handle the inconsistent JSON response schemas, below
      *           is just a hacky way to handle the most often found patterns from trial and error.
      */
     @SerializedName("science")
     @Expose
     private List<Problem> science;

     @SerializedName("computers")
     @Expose
     private List<Problem> computers;

     @SerializedName("nature")
     @Expose
     private List<Problem> nature;

     @SerializedName("famous people")
     @Expose
     private List<Problem> famouspeople;

     @SerializedName("video games")
     @Expose
     private List<Problem> videogames;

     @SerializedName("anime and manga")
     @Expose
     private List<Problem> animeandmanga;

     @SerializedName("anime_manga")
     @Expose
     private List<Problem> anime_manga;

     @SerializedName("technology")
     @Expose
     private List<Problem> technology;

     @SerializedName("programming")
     @Expose
     private List<Problem> programming;

     @SerializedName("math")
     @Expose
     private List<Problem> math;



     @SerializedName("Science")
     @Expose
     private List<Problem> Science;

     @SerializedName("Computers")
     @Expose
     private List<Problem> Computers;

     @SerializedName("Nature")
     @Expose
     private List<Problem> Nature;

     @SerializedName("Famous People")
     @Expose
     private List<Problem> FamousPeople;

     @SerializedName("Video Games")
     @Expose
     private List<Problem> VideoGames;

     @SerializedName("Anime and Manga")
     @Expose
     private List<Problem> AnimeandManga;

     @SerializedName("Technology")
     @Expose
     private List<Problem> Technology;

     @SerializedName("Programming")
     @Expose
     private List<Problem> Programming;

     @SerializedName("Math")
     @Expose
     private List<Problem> Math;




     @SerializedName("science_problems")
     @Expose
     private List<Problem> science_problems;

     @SerializedName("computers_problems")
     @Expose
     private List<Problem> computers_problems;

     @SerializedName("nature_problems")
     @Expose
     private List<Problem> nature_problems;

     @SerializedName("famous_people_problems")
     @Expose
     private List<Problem> famous_people_problems;

     @SerializedName("video_games_problems")
     @Expose
     private List<Problem> video_games_problems;

     @SerializedName("anime_manga_problems")
     @Expose
     private List<Problem> anime_manga_problems;

     @SerializedName("technology_problems")
     @Expose
     private List<Problem> technology_problems;

     @SerializedName("programming_problems")
     @Expose
     private List<Problem> programming_problems;

     @SerializedName("math_problems")
     @Expose
     private List<Problem> math_problems;




     @SerializedName("science problems")
     @Expose
     private List<Problem> scienceproblems;

     @SerializedName("computer problems")
     @Expose
     private List<Problem> computersproblems;

     @SerializedName("nature problems")
     @Expose
     private List<Problem> natureproblems;

     @SerializedName("famouspeopleproblems")
     @Expose
     private List<Problem> famouspeopleproblems;

     @SerializedName("video game problems")
     @Expose
     private List<Problem> videogamesproblems;

     @SerializedName("anime and manga problems")
     @Expose
     private List<Problem> animeandmangaproblems;

     @SerializedName("technology problems")
     @Expose
     private List<Problem> technologyproblems;

     @SerializedName("programming problems")
     @Expose
     private List<Problem> programmingproblems;

     @SerializedName("math problems")
     @Expose
     private List<Problem> mathproblems;




     @SerializedName("Science Problems")
     @Expose
     private List<Problem> ScienceProblems;

     @SerializedName("Computer Problems")
     @Expose
     private List<Problem> ComputersProblems;

     @SerializedName("Nature Problems")
     @Expose
     private List<Problem> NatureProblems;

     @SerializedName("Famous People Problems")
     @Expose
     private List<Problem> FamousPeopleProblems;

     @SerializedName("Video Game Problems")
     @Expose
     private List<Problem> VideoGamesProblems;

     @SerializedName("Anime and Manga Problems")
     @Expose
     private List<Problem> AnimeandMangaProblems;

     @SerializedName("Technology Problems")
     @Expose
     private List<Problem> TechnologyProblems;

     @SerializedName("Programming Problems")
     @Expose
     private List<Problem> ProgrammingProblems;

     @SerializedName("Math Problems")
     @Expose
     private List<Problem> MathProblems;




     @SerializedName("1")
     @Expose
     private Problem _1;

     @SerializedName("2")
     @Expose
     private Problem _2;

     @SerializedName("3")
     @Expose
     private Problem _3;

     @SerializedName("4")
     @Expose
     private Problem _4;

     @SerializedName("5")
     @Expose
     private Problem _5;

     @SerializedName("6")
     @Expose
     private Problem _6;

     @SerializedName("7")
     @Expose
     private Problem _7;

     @SerializedName("8")
     @Expose
     private Problem _8;

     @SerializedName("9")
     @Expose
     private Problem _9;

     @SerializedName("10")
     @Expose
     private Problem _10;




     @SerializedName("problem1")
     @Expose
     private Problem problem1;

     @SerializedName("problem2")
     @Expose
     private Problem problem2;

     @SerializedName("problem3")
     @Expose
     private Problem problem3;

     @SerializedName("problem4")
     @Expose
     private Problem problem4;

     @SerializedName("problem5")
     @Expose
     private Problem problem5;

     @SerializedName("problem6")
     @Expose
     private Problem problem6;

     @SerializedName("problem7")
     @Expose
     private Problem problem7;

     @SerializedName("problem8")
     @Expose
     private Problem problem8;

     @SerializedName("problem9")
     @Expose
     private Problem problem9;

     @SerializedName("problem10")
     @Expose
     private Problem problem10;




     @SerializedName("1. Educational problem")
     @Expose
     private Problem Educationalproblem1;

     @SerializedName("2. Educational problem")
     @Expose
     private Problem Educationalproblem2;

     @SerializedName("3. Educational problem")
     @Expose
     private Problem Educationalproblem3;

     @SerializedName("4. Educational problem")
     @Expose
     private Problem Educationalproblem4;

     @SerializedName("5. Educational problem")
     @Expose
     private Problem Educationalproblem5;

     @SerializedName("6. Educational problem")
     @Expose
     private Problem Educationalproblem6;

     @SerializedName("7. Educational problem")
     @Expose
     private Problem Educationalproblem7;

     @SerializedName("8. Educational problem")
     @Expose
     private Problem Educationalproblem8;

     @SerializedName("9. Educational problem")
     @Expose
     private Problem Educationalproblem9;

     @SerializedName("10. Educational problem")
     @Expose
     private Problem Educationalproblem10;




     @SerializedName("Question 1")
     @Expose
     private Problem Question1;

     @SerializedName("Question 2")
     @Expose
     private Problem Question2;

     @SerializedName("Question 3")
     @Expose
     private Problem Question3;

     @SerializedName("Question 4")
     @Expose
     private Problem Question4;

     @SerializedName("Question 5")
     @Expose
     private Problem Question5;

     @SerializedName("Question 6")
     @Expose
     private Problem Question6;

     @SerializedName("Question 7")
     @Expose
     private Problem Question7;

     @SerializedName("Question 8")
     @Expose
     private Problem Question8;

     @SerializedName("Question 9")
     @Expose
     private Problem Question9;

     @SerializedName("Question 10")
     @Expose
     private Problem Question10;




     private List<Problem> educational_problems;
     private List<Problem> educational_questions;
     private List<Problem> questions;
     private List<Problem> Questions;
     private List<Problem> problems;
     private List<Problem> Problems;


     public List<Problem> getProblems() {
          List<Problem> problems = new ArrayList<Problem>();

          if (educational_problems != null) {
               return educational_problems;
          } else if (educational_questions != null) {
               return educational_questions;
          } else if (questions != null) {
               return questions;
          } else if (Questions != null) {
               return Questions;
          } else if (this.problems != null) {
               return this.problems;
          } else if (Problems != null) {
               return Problems;
          } else if (_1 != null ||
                  _2 != null ||
                  _3 != null ||
                  _4 != null ||
                  _5 != null ||
                  _6 != null ||
                  _7 != null ||
                  _8 != null ||
                  _9 != null ||
                  _10 != null) {
                    if (_1 != null) problems.add(_1);
                    if (_2 != null) problems.add(_2);
                    if (_3 != null) problems.add(_3);
                    if (_4 != null) problems.add(_4);
                    if (_5 != null) problems.add(_5);
                    if (_6 != null) problems.add(_6);
                    if (_7 != null) problems.add(_7);
                    if (_8 != null) problems.add(_8);
                    if (_9 != null) problems.add(_9);
                    if (_10 != null) problems.add(_10);
          } else if (problem1 != null ||
                  problem2 != null ||
                  problem3 != null ||
                  problem4 != null ||
                  problem5 != null ||
                  problem6 != null ||
                  problem7 != null ||
                  problem8 != null ||
                  problem9 != null ||
                  problem10 != null) {
                    if (problem1 != null) problems.add(problem1);
                    if (problem2 != null) problems.add(problem2);
                    if (problem3 != null) problems.add(problem3);
                    if (problem4 != null) problems.add(problem4);
                    if (problem5 != null) problems.add(problem5);
                    if (problem6 != null) problems.add(problem6);
                    if (problem7 != null) problems.add(problem7);
                    if (problem8 != null) problems.add(problem8);
                    if (problem9 != null) problems.add(problem9);
                    if (problem10 != null) problems.add(problem10);
          } else if (Question1 != null ||
                  Question2 != null ||
                  Question3 != null ||
                  Question4 != null ||
                  Question5 != null ||
                  Question6 != null ||
                  Question7 != null ||
                  Question8 != null ||
                  Question9 != null ||
                  Question10 != null) {
                    if (Question1 != null) problems.add(Question1);
                    if (Question2 != null) problems.add(Question2);
                    if (Question3 != null) problems.add(Question3);
                    if (Question4 != null) problems.add(Question4);
                    if (Question5 != null) problems.add(Question5);
                    if (Question6 != null) problems.add(Question6);
                    if (Question7 != null) problems.add(Question7);
                    if (Question8 != null) problems.add(Question8);
                    if (Question9 != null) problems.add(Question9);
                    if (Question10 != null) problems.add(Question10);
          } else if (Educationalproblem1 != null ||
                  Educationalproblem2 != null ||
                  Educationalproblem3 != null ||
                  Educationalproblem4 != null ||
                  Educationalproblem5 != null ||
                  Educationalproblem6 != null ||
                  Educationalproblem7 != null ||
                  Educationalproblem8 != null ||
                  Educationalproblem9 != null ||
                  Educationalproblem10 != null) {
                    if (Educationalproblem1 != null) problems.add(Educationalproblem1);
                    if (Educationalproblem2 != null) problems.add(Educationalproblem2);
                    if (Educationalproblem3 != null) problems.add(Educationalproblem3);
                    if (Educationalproblem4 != null) problems.add(Educationalproblem4);
                    if (Educationalproblem5 != null) problems.add(Educationalproblem5);
                    if (Educationalproblem6 != null) problems.add(Educationalproblem6);
                    if (Educationalproblem7 != null) problems.add(Educationalproblem7);
                    if (Educationalproblem8 != null) problems.add(Educationalproblem8);
                    if (Educationalproblem9 != null) problems.add(Educationalproblem9);
                    if (Educationalproblem10 != null) problems.add(Educationalproblem10);
          } else if (science != null ||
               computers != null ||
               nature != null ||
               famouspeople  != null ||
               videogames != null ||
               animeandmanga != null ||
               anime_manga != null ||
               technology != null ||
               programming  != null ||
               math  != null) {
               if (science != null) problems.addAll(science);
               if (computers != null) problems.addAll(computers);
               if (nature != null) problems.addAll(nature);
               if (famouspeople != null) problems.addAll(famouspeople);
               if (videogames != null) problems.addAll(videogames);
               if (animeandmanga != null) problems.addAll(animeandmanga);
               if (anime_manga != null) problems.addAll(anime_manga);
               if (technology != null) problems.addAll(technology);
               if (programming != null) problems.addAll(programming);
               if (math != null) problems.addAll(math);
          } else if (Science != null ||
               Computers != null ||
               Nature != null ||
               FamousPeople != null ||
               VideoGames != null ||
               AnimeandManga != null ||
               Technology != null ||
               Programming != null ||
               Math != null) {
               if (Science != null) problems.addAll(Science);
               if (Computers != null) problems.addAll(Computers);
               if (Nature != null) problems.addAll(Nature);
               if (FamousPeople != null) problems.addAll(FamousPeople);
               if (VideoGames != null) problems.addAll(VideoGames);
               if (AnimeandManga != null) problems.addAll(AnimeandManga);
               if (Technology != null) problems.addAll(Technology);
               if (Programming != null) problems.addAll(Programming);
               if (Math != null) problems.addAll(Math);
          } else if (science_problems != null ||
               computers_problems != null ||
               nature_problems != null ||
               famous_people_problems != null ||
               video_games_problems != null ||
               anime_manga_problems != null ||
               technology_problems != null ||
               programming_problems != null ||
               math_problems != null) {
               if (science_problems != null) problems.addAll(science_problems);
               if (computers_problems != null) problems.addAll(computers_problems);
               if (nature_problems != null) problems.addAll(nature_problems);
               if (famous_people_problems != null) problems.addAll(famous_people_problems);
               if (video_games_problems != null) problems.addAll(video_games_problems);
               if (anime_manga_problems != null) problems.addAll(anime_manga_problems);
               if (technology_problems != null) problems.addAll(technology_problems);
               if (programming_problems != null) problems.addAll(programming_problems);
               if (math_problems != null) problems.addAll(math_problems);
          } else if (scienceproblems != null ||
               computersproblems != null ||
               natureproblems != null ||
               famouspeopleproblems != null ||
               videogamesproblems != null ||
               animeandmangaproblems != null ||
               technologyproblems != null ||
               programmingproblems != null ||
               mathproblems != null) {
               if (scienceproblems != null) problems.addAll(scienceproblems);
               if (computersproblems != null) problems.addAll(computersproblems);
               if (natureproblems != null) problems.addAll(natureproblems);
               if (famouspeopleproblems != null) problems.addAll(famouspeopleproblems);
               if (videogamesproblems != null) problems.addAll(videogamesproblems);
               if (animeandmangaproblems != null) problems.addAll(animeandmangaproblems);
               if (technologyproblems != null) problems.addAll(technologyproblems);
               if (programmingproblems != null) problems.addAll(programmingproblems);
               if (mathproblems != null) problems.addAll(mathproblems);
          }  else if (ScienceProblems != null ||
               ComputersProblems != null ||
               NatureProblems != null ||
               FamousPeopleProblems != null ||
               VideoGamesProblems != null ||
               AnimeandMangaProblems != null ||
               TechnologyProblems != null ||
               ProgrammingProblems != null ||
               MathProblems != null) {
               if (ScienceProblems != null) problems.addAll(ScienceProblems);
               if (ComputersProblems != null) problems.addAll(ComputersProblems);
               if (NatureProblems != null) problems.addAll(NatureProblems);
               if (FamousPeopleProblems != null) problems.addAll(FamousPeopleProblems);
               if (VideoGamesProblems != null) problems.addAll(VideoGamesProblems);
               if (AnimeandMangaProblems != null) problems.addAll(AnimeandMangaProblems);
               if (TechnologyProblems != null) problems.addAll(TechnologyProblems);
               if (ProgrammingProblems != null) problems.addAll(ProgrammingProblems);
               if (MathProblems != null) problems.addAll(MathProblems);
          }

          return problems;
     }
}