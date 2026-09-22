package com.testecanvas.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.testecanvas.traco.ElemTraco;
import com.testecanvas.traco.MovEnum;

import java.util.ArrayList;

public class DesenhoCanvas extends View {

    // dimensões do canvas
    private int width;
    private int height;
    private float xC;               // coord x do centro
    private float yC;               // coord y do centro


    private Paint paint;

    // definindo valores default
    int corDefault = Color.CYAN;        // cor do desenho/pintura
    int corBola = Color.YELLOW;         // cor da bolinha do cursor
    float espessura = 5f;               // espessura do traço
    int passo = 50;                     // tamanho em pixels do elemento de traco
    int dBola = 10;                     // diâmetro da bola

    // coleção de partes do traço
    ArrayList<ElemTraco> traco;

    public DesenhoCanvas(Context context) {
        super(context);
    }

    public DesenhoCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        traco = new ArrayList<>();
        paint = new Paint();
        paint.setColor(corDefault);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(espessura);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        xC = (float) width / 2;
        yC = (float) height / 2;
        desenhaTela(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        invalidate();
    }

    /*
            COMANDOS EXTERNOS
     */

    public void atualizaImagem() {
        invalidate();
    }

    public void esq() {
        traco.add(new ElemTraco(MovEnum.ESQ, corDefault));
    }

    public void dir() {
        traco.add(new ElemTraco(MovEnum.DIR, corDefault));
    }

    public void cima() {
        traco.add(new ElemTraco(MovEnum.CIMA, corDefault));
    }

    public void baixo() {
        traco.add(new ElemTraco(MovEnum.BAIXO, corDefault));
    }

    public void cimaDir() {
        traco.add(new ElemTraco(MovEnum.CIMA_DIR, corDefault));
    }

    public void cimaEsq() {
        traco.add(new ElemTraco(MovEnum.CIMA_ESQ, corDefault));
    }

    public void baixoDir() {
        traco.add(new ElemTraco(MovEnum.BAIXO_DIR, corDefault));
    }

    public void baixoEsq() {
        traco.add(new ElemTraco(MovEnum.BAIXO_ESQ, corDefault));
    }

    public void reset() {
        traco.clear();
    }




    /*
    =========       ROTINAS PARA CONSTRUÇÃO DA ÁREA DE DESENHO (NO CANVAS)
     */

    private void desenhaTela(Canvas canvas) {
        float x = xC;
        float y = yC;
        float deltaX = 0;
        float deltaY = 0;
        int cor = corDefault;
        for (int i = 0; i < traco.size(); i++) {
            switch (traco.get(i).mov) {
                case CIMA:
                    deltaX = 0;
                    deltaY = -passo;
                    break;
                case BAIXO:
                    deltaX = 0;
                    deltaY = passo;
                    break;
                case ESQ:
                    deltaX = -passo;
                    deltaY = 0;
                    break;
                case DIR:
                    deltaX = passo;
                    deltaY = 0;
                    break;
                case CIMA_DIR:
                    deltaX = passo;
                    deltaY = -passo;
                    break;
                case CIMA_ESQ:
                    deltaX = -passo;
                    deltaY = -passo;
                    break;
                case BAIXO_DIR:
                    deltaX = passo;
                    deltaY = passo;
                    break;
                case BAIXO_ESQ:
                    deltaX = -passo;
                    deltaY = passo;
                    break;
            }
            paint.setColor(traco.get(i).cor);
            canvas.drawLine(x, y, x + deltaX, y + deltaY, paint);
            x += deltaX;
            y += deltaY;
        }
        paint.setColor(corBola);
        canvas.drawCircle(x, y, dBola, paint);
    }

}
