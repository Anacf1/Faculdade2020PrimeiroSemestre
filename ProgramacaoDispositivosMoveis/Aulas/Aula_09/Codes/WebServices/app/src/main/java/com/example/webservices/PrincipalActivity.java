package com.example.webservices;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class PrincipalActivity extends AppCompatActivity {

    Encomenda encomenda = new Encomenda();

    EditText editTextCodigoServico;
    EditText editTextCEPOrigem;
    EditText editTextCEPDestino;

    String TAG = "Response";
    String getServico;
    String getOrigem;
    String getDestino;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.principal);

        editTextCodigoServico = findViewById(R.id.codigoServico);
        editTextCEPOrigem = findViewById(R.id.cepOrigem);
        editTextCEPDestino = findViewById(R.id.cepDestino);

    }



    public void calcularPrazo(View view) {
        Button buttonCalcularPrazo = findViewById(R.id.calcularPrazo);
        buttonCalcularPrazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigoServico = editTextCodigoServico.getText().toString();
                String cepOrigem = editTextCEPOrigem.getText().toString();
                String cepDestino = editTextCEPDestino.getText().toString();


            }
        });
    }

    /*Metodo calcular:
        Nele está definido todo o código necessário para o consumo das informações de nosso Web Service.*/
    public void calcular() throws IOException, XmlPullParserException {

        String SOAP_ACTION =  "http://tempuri.org/CalcPrazo";                        /*SOAP_ACTION: Possui o caminho completo do método do Web Service que será invocado*/
        String METHOD_NAME = "CalcPrazo";                                            /*METHOD_NAME: Possui o nome do método que será invocado pelo Web Service*/
        String NAMESPACE = "http://tempuri.org";                                    /*NAMESPACE: Possui o caminho onde o Web Service está hospedado*/
        String URL = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx";    /*URL: Possui o caminho do arquivo com o descritivo de todas as funções do Web Service*/

        try {
            /*O objeto SoapObject representará o caminho onde está hospedado nosso Web Service
            (namespace) e o nome do método a ser chamado (method_name)*/
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("editTextCodigoServico", getServico);
            request.addProperty("editTextCEPOrigem", getOrigem);
            request.addProperty("editTextCEPDestino", getDestino);

            /*A classe SoapSerializationEnvelope é responsável por empacotar nossa requisição SOAP.
                onde SoapEnvolope.VER11 corresponde à versão 1.1 do SOAP.*/
            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            /*O método .dotNet = true define a compatibilidade com o padrão de codificação .net*/
            soapEnvelope.dotNet = true;

            /*.setOutputSoapObject(Request): Insere a requisição montada no envelope*/
            soapEnvelope.setOutputSoapObject(request);

            /*Criando o objeto responsável pela comunicação
                A classe HttpTransportSE é responsável pela chamada ao servidor de aplicação,
                passando-se a URL como argumento.*/
            HttpTransportSE transport = new HttpTransportSE(URL);

            /*Invocando o Web Service
                Recebe como parâmetro a localização de nosso Web Service (SOAP_ACTION) e nossa
                requisição SOAP “envelopada” pelo objeto do tipo SoapSerializarionEnvelope(soapEnvelope)*/
            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject response = (SoapObject) soapEnvelope.getResponse();

            response = (SoapObject) response.getProperty("Servicos");
            response = (SoapObject) response.getProperty("editTextCodigoServico");

            encomenda = new Encomenda();
            encomenda.setCodigoServico(response.getProperty("Codigo").toString());
            encomenda.setPrazoEntrega(response.getProperty("PrazoEntrega").toString());
            encomenda.setEntregaDomiciliar(response.getProperty("EntregaDomiciliar").toString());
            encomenda.setEntregaSabado(response.getProperty("EntregaSabado").toString());

        }catch (Exception e){
            Log.e(TAG, "ERROR: " + e.getMessage());
        }
    }

    /*Classe AsyncTask: Seu objetivo é encapsular a implementação das classes
        Threads e Handler, tornando muito mais fácil a implementação desses
        conceitos em nosso aplicativo.

        AsyncTask<Void, Void, Void>:
            [AsyncTask< Params,Progress, Result >] possui três tipos genéricos que correspondem respectivamente a:
                Params:
                    • Tipo de parâmetro de entrada;
                    • Argumentos que podemos passar por parâmetro ao método execute (params...);
                    • Devemos usar “Void”, se não desejarmos passar algum parâmetro.
                Progress:
                    • Tipo de parâmetro de incremento;
                    • Argumentos usados para informar o progresso do processo;
                    • Utilizados no método publishProgress() e onProgressUpdate().
                Result:
                    • Tipo de parâmetro de retorno;
                    • Usado no método onInBackground() como sendo o seu retorno e o onPostExecute(Result).
        */
    private class AsyncCallWS extends AsyncTask<Void, Void, Void> {


        /*Metodo doInBackground:
            Executado em background por uma Thread;
            Chamado após o método onPreExecute() ser finalizado;
            Nesse método deve ser implementado o processamento principal,
                como, por exemplo, uma busca em um Web Service ou qualquer
                outro processamento que demande tempo;
            Pode retornar um parâmetro para o método onPostExecute().*/
        @Override
        protected Void doInBackground(Void... voids) {
            Log.i(TAG, "doInBackground");
            try {
                calcular();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return null;
        }

        /*Metodo onPostExecute:
            Chamado logo após o término do método onInBackground();
            Recebe o retorno do método onInBackground();
            Responsável por terminar a execução de nosso processo, como,
                por exemplo, fechar nossa janela de progresso.*/
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");
            AlertDialog.Builder dialogo = new AlertDialog.Builder(PrincipalActivity.this);
            dialogo.setTitle("Resultado");
            dialogo.setNeutralButton("OK", null);
            dialogo.setMessage(
                    "Código da Encomenda: " + encomenda.getCodigoServico() + "\n " +
                            "Prazo de Entrega: " + encomenda.getPrazoEntrega() + "\n " +
                            "Entrega domiciliar: " + encomenda.getEntregaDomiciliar() + "\n " +
                            "Entrega sábado: " + encomenda.getEntregaSabado());
            dialogo.show();
        }
    }
}

