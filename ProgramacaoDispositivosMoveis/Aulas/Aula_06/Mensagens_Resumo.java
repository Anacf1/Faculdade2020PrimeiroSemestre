1. Toast
public class PrincipalToastActivity extends AppCompatActivity {

    Button botao;
	
	@Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_toast);

        botao = findViewById(R.id.botaoToast);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
				*LayoutInflater layoutInflater = getLayoutInflater();
                *View layout = layoutInflater.inflate(R.layout.toast_mensagem, (ViewGroup) findViewById(R.id.custom_toast_layout_id));

                ImageView imagem = (ImageView) findViewById(R.id.imagem);
                imagem.setImageResource(R.drawable.ic_launcher_background);

                TextView texto = (TextView) findViewById(R.id.texto);
                texto.setText("Exercicio Toast(layout)");

				/*
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
				*/

            }
        });

    }   
}

2. Notification
public class PrincipalNotificationActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_notification);

        Button botao = findViewById(R.id.botaoPrincipal);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNotification();
            }

            protected void startNotification(){
				/*
                NotificationCompat.Builder notificacao = new NotificationCompat.Builder(PrincipalNotificationActivity.this);
                notificacao.setContentTitle("Notificação Title");
                notificacao.setContentText("Notificao Text");
                notificacao.setTicker("Atenção");
                notificacao.setSmallIcon(R.drawable.ic_launcher_background);
				*/
				
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(PrincipalNotificationActivity.this);
                stackBuilder.addParentStack(NotificacaoActivity.class);

                Intent intent = new Intent(PrincipalNotificationActivity.this, NotificacaoActivity.class);

                stackBuilder.addNextIntent(intent);

                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

				/*
                notificacao.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, notificacao.build());
				*/
            }

        });
    }
	
3. Alert
public class PrincipalAlertDialogActitivy extends AppCompatActivity {

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_alertdialog);

        Button botao03 = findViewById(R.id.botao03);
    }
	
	public void mostarMensagemBotao03(View view){
		/*
        AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalAlertDialogActitivy.this);
        builder.setTitle("Botão 03");
        builder.setMessage("Você pertou o botão 03");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Botão Ok clicado", Toast.LENGTH_LONG).show();
            }
        })*/
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Botao No clicado", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Botao Cancel clicado", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
	
4. Progress Dialog

O método setProgressStyle define o estilo da ProgressDialog, podendo ser STYLE_HORIZONTAL(Loop Finito) ou STYLE_SPINNER(Loop Infinito).


public class PrincipalProgressDialogActivity extends AppCompatActivity {

    ProgressDialog barraProgresso;
	/*Handler manipuladorBarraProgresso = new Handler();*/
    int statusBarraProgresso = 0;
    long TAMANHO = 0;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_progressdialog);
        addListenerOnButton();
    }

    public void addListenerOnButton(){
        Button botaoEnviarAquivo = (Button) findViewById(R.id.botaoEnviarArquivo);
        botaoEnviarAquivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				/*
				barraProgresso = new ProgressDialog(v.getContext());
                barraProgresso.setCancelable(true);
                barraProgresso.setMessage("Enviando . . .");
                barraProgresso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                barraProgresso.setProgress(0);
                barraProgresso.setMax(100);
                barraProgresso.show();
				*/
				
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(statusBarraProgresso < 100){
                            statusBarraProgresso = doSomeTasks();
                            try {
                                Thread.sleep(1000);
                            }catch (InterruptedException in){
                                in.printStackTrace();
                                /*manipuladorBarraProgresso.post(new Runnable() {*/
                                    @Override
                                    public void run() {
										/*barraProgresso.setProgress(statusBarraProgresso);*/
                                    }
                                });
                            }
                        }
                        if(statusBarraProgresso >= 100){
                            try {
                                Thread.sleep(2000);
                            }catch (InterruptedException in){
                                in.printStackTrace();
								/*
                                barraProgresso.dismiss();
								*/
                            }
                        }
                    }
                });

            }
        });
    }
}

5. DatePicker
public void setarDataAtual(){
        Calendar calendar = Calendar.getInstance();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);

        textoData = (TextView) findViewById(R.id.textoData);
        textoData.setText(new StringBuilder()
                .append(dia)
                .append("-")
                .append(mes+1)
                .append("-").append(ano));

		/*
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.init(ano, mes,dia, null);
		*/
    }
	
5.1 DatePickerDialog
public void adicionarListenerBotaoalterarData(){
        Button botaoAlterarData = (Button) findViewById(R.id.alterarData);
        botaoAlterarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*showDialog(DATE_DIALOG_ID);*/
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
		/*
        DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dayOfMonth = dia;
                month = mes;
                year = ano;

                textoData.setText(new StringBuilder()
                        .append(dia)
                        .append("-")
                        .append(mes+1)
                        .append("-")
                        .append(ano)
                        .append(" "));

                datePicker.init(ano, mes, dia, null);
				*/
            }
        };

        switch (id) {
            case DATE_DIALOG_ID:
                /*return new DatePickerDialog(this, datePickerListener, ano, mes, dia);*/
        }
        return null;
		
    }
	
6. TimePicker
public void setarHoraAtual(){
        Calendar calendar = Calendar.getInstance();
        hora = calendar.get(Calendar.HOUR_OF_DAY);
        minuto = calendar.get(Calendar.MINUTE);

        textoHora = findViewById(R.id.textoHora);
        textoHora.setText(new StringBuilder()
                .append(String.valueOf(hora))
                .append(" : ")
                .append(String.valueOf(minuto)));

		/*
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setCurrentHour(hora);
        timePicker.setCurrentMinute(minuto);
		*/
    }
	
6.1 TimePickerDialog
public void adicionarListenerBotaoAlterarHora(View view){
        Button button = (Button) findViewById(R.id.alterarHora);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*showDialog(TIME_DIALOG_ID);*/
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id){
		/*
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hora = hourOfDay;
                minuto = minute;

                textoHora.setText(new StringBuilder()
                        .append(String.valueOf(hora))
                        .append(" : ")
                        .append(String.valueOf(minuto))
                        );

                timePicker.setCurrentHour(hora);
                timePicker.setCurrentMinute(minuto);
            }
        };*/

        switch (id){
            case TIME_DIALOG_ID:
                /*return new TimePickerDialog(this, timeSetListener, hora, minuto, false);*/
        }
        return null;
    }

