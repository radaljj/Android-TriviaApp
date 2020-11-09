package com.example.triviaapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.triviaapp.controller.AppController;
import com.example.triviaapp.model.Question;
import static com.example.triviaapp.controller.AppController.TAG;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {



    String url="https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    ArrayList<Question> questionArrayList=new ArrayList<>();



    public List<Question> getQuestions(final AnswerInter callback){

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i <response.length() ; i++) {
                    try {
                        Question question= new Question();
                        question.setPitanje(response.getJSONArray(i).get(0).toString());
                        question.setTacnost(response.getJSONArray(i).getBoolean(1));

                        questionArrayList.add(question);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(null!=callback)
                    {
                        callback.processFinished(questionArrayList );
                    }
                }

                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

return questionArrayList;
    }

}
