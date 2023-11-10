package br.ifsul.yesorno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String URL = "https://yesno.wtf/";
    private Retrofit retrofitYESORNO;
    Button buttonDecide;
    private EditText textInput;
    private TextInputLayout a;
    private TextView answerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonDecide = findViewById(R.id.buttonDecide);
        textInput = findViewById(R.id.textInput);
        answerText = findViewById(R.id.answerText);

        retrofitYESORNO = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        buttonDecide.setOnClickListener(this);
    }

    public void consultarYESORNO() {
        RestService restService = retrofitYESORNO.create(RestService.class);
        Call<YON> call = restService.consultarYESORNO();

        call.enqueue(new Callback<YON>() {
            @Override
            public void onResponse(Call<YON> call, Response<YON> response) {
                if (response.isSuccessful()) {
                    YON answer = response.body();
                    answerText.setText(answer.getAnswer());
                    textInput.setText("");
                }
            }

            @Override
            public void onFailure(Call<YON> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.buttonDecide){
            consultarYESORNO();
        }
    }
}