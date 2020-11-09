package com.example.triviaapp.data;

import android.util.Log;

import com.example.triviaapp.model.Question;

import java.util.ArrayList;

public interface AnswerInter {

    void processFinished(ArrayList<Question>questions);
}
