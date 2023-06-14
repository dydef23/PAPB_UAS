package com.example.papb_uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.media.MediaParser;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RequestQueue reqQue;
    RecyclerView rvList;
    ListAdapter adapter;
    ArrayList<ToDoList> list;
    Gson gson;
    Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();
        list = new ArrayList<>();
        rvList = findViewById(R.id.rvList);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        reqQue = Volley.newRequestQueue(this);
        this.ctx = this;
        jsonParse();
    }

    public void jsonParse(){
        String url = "https://mgm.ub.ac.id/todo.php";
        Handler h = new Handler(Looper.getMainLooper());
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        list = gson.fromJson(response, new TypeToken<List<ToDoList>>() {
                        }.getType());
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new ListAdapter(ctx, list);
                                rvList.setAdapter(adapter);
                            }
                        });
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                reqQue.add(stringRequest);
            }
        });
        t.start();
    }
}