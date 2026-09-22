package com.testecanvas;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.testecanvas.canvas.DesenhoCanvas;

public class MainActivity extends AppCompatActivity {

    private DesenhoCanvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        canvas = findViewById(R.id.canvas);
    }





    /*
            GERENCIAMENTO DE CLIQUES
     */

    public void onClickHandleBtns(View view) {
        String btnSel = view.getResources().getResourceEntryName(view.getId());
        switch (btnSel) {
            case "btnCima":
                canvas.cima();
                break;
            case "btnBaixo":
                canvas.baixo();
                break;
            case "btnEsq":
                canvas.esq();
                break;
            case "btnDir":
                canvas.dir();
                break;
            case "btnReset":
                canvas.reset();
                break;
            case "btnCesq":
                canvas.cimaEsq();
                break;
            case "btnCdir":
                canvas.cimaDir();
                break;
            case "btnBdir":
                canvas.baixoDir();
                break;
            case "btnBesq":
                canvas.baixoEsq();
                break;
        }
        canvas.atualizaImagem();
    }


}