1. Toast [android.widget.Toast]
	Quando desejamos exibir mensagens de alertas para o usuário
	
	Esta exibe uma pequena tela, que pode ser personalizada, sem 
	caráter permanente, ou seja, desaparecendo sem qualquer tipo 
	de intervenção do usuário.
	
	/*Toast toast = Toast.makeText(contexto, texto, duracao)*/ 
		duração:. Constantes: Toast.LENGTH_SHORT e Toast.LENGTH_LONG.

	/*toast.setGravity(Gravity.CENTER,0,0);*/
		setar o pocisionamento da mensagem
		
	/*toast.show*/
		para exibir essa mensagem		
		
	1.1 Toast Personalizado
		botao = findViewById(R.id.botao);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View layout = layoutInflater.inflate(R.layout.mensagem, (ViewGroup) findViewById(R.id.custom_toast_layout_id));
				
													R.layout.mensagem -> Nome do arquivo de layout 
													findViewById(R.id.custom_toast_layout_id) -> Id do arquivo de layout 

                TextView texto = (TextView) findViewById(R.id.texto);
                texto.setText("Exercicio Toast(layout)");

                *Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.setDuration(Toast.LENGTH_LONG);
                *toast.setView(layout);
                toast.show();

            }
        });
		
		
java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.ImageView.setImageResource(int)' on a null object reference

2. Notificações
	Mensagem que aparece na barra de status Android. Seu objetivo é notificar ao 
	usuário sobre algum evento
	
		1. Opções:.
			2.1.1 Vibração
			2.1.2 Som
			2.1.3 Luz
			
		A partir do Android 4.1 Jelly Bean, as notificações podem possuir os recursos:
		
			2.1.4 VISUALIZAÇÃO EXPANSÍVEL
				Podemos expandir uma notificacao usando o gesto "apertar e ampliar"
			2.1.5 BOTÕES DE AÇÃO
				Podemos adicionar até três botões
			2.1.6 ESTILOS VARIADOS
				BigTextStyle: Exibe um TextView com diversas linhas.
				BigInboxStyle: Exibe uma lista de informações.
			2.1.7 TAMANHO MAIOR
				Podem ser tão altas quanto 256 dp.
			
		2. Para implementar uma notificação:
			2.1 Notification.Builder para configurar nossa notificação
			2.2 Obter a classe NotificationManager
			
		*NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		*NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		protected void startNotification(){
                *NotificationCompat.Builder notificacao = new NotificationCompat.Builder(ActivityPrincipal.this);
                notificacao.setContentTitle("Notificação Title");
                notificacao.setContentText("Notificao Text");
                notificacao.setTicker("Atenção");
                notificacao.setSmallIcon(R.drawable.ic_launcher_background);

				/*TaskStackBuilder -> construtor(builder) de pilha(stack) de tarefas(task)*/
                *TaskStackBuilder stackBuilder = TaskStackBuilder.create(ActivityPrincipal.this);
                stackBuilder.addParentStack(ActivityNotificacao.class);

                Intent intent = new Intent(ActivityPrincipal.this, ActivityNotificacao.class);

                stackBuilder.addNextIntent(intent);

                *PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                notificacao.setContentIntent(pendingIntent);

                *NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, notificacao.build());
            }		
		
3. AlertDialog
	Pode exibir uma caixa de diálogo com um título de até três botões.
	Diferente da classe Toast, podemos interagir com o usuário pressionando 
	botões.

	Existem três regiões na AlertDialog que devemos configurar:
		3.1 Titulo
		3.2 Mensagem
		3.3 Botões de ação

	public void mostarMensagemBotao03(View view){
		/*AlertDialog foi criada no contexto de nossa activity(MainActivity), ou seja, no contexto que será exibida*/
        *AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalAlertDialogActitivy.this);
        builder.setTitle("Botão 03");
        builder.setMessage("Você pertou o botão 03");
        *builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Botão Ok clicado", Toast.LENGTH_LONG).show();
            }
        });
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
		
4. ProgressDialog
	Exibe um indicador de progresso e uma mensagem de texto opcional.

	Seu range de progresso é de 0 a 10000.

	Quando o sistema for efetuar uma operação demorada, podemos implementar a ProgressDialog. 
	Com isso, o usuário não pensará que o aplicativo travou.	
	
	public void addListenerOnButton(){
        Button botaoEnviarAquivo = (Button) findViewById(R.id.botaoEnviarArquivo);
        botaoEnviarAquivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *barraProgresso = new ProgressDialog(v.getContext());
                barraProgresso.setCancelable(true);
                barraProgresso.setMessage("Enviando . . .");
                barraProgresso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                barraProgresso.setProgress(0);
                barraProgresso.setMax(100);
                barraProgresso.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(statusBarraProgresso < 100){
                            statusBarraProgresso = doSomeTasks();
                            try {
                                Thread.sleep(1000);
                            }catch (InterruptedException in){
                                in.printStackTrace();
                                manipuladorBarraProgresso.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        barraProgresso.setProgress(statusBarraProgresso);
                                    }
                                });
                            }
                        }
                        if(statusBarraProgresso >= 100){
                            try {
                                Thread.sleep(2000);
                            }catch (InterruptedException in){
                                in.printStackTrace();
                                barraProgresso.dismiss();
                            }
                        }
                    }
                });

            }
        });
    }

		4.1 Métodos
			• setCancelable(): Define se ProgressDialog pode ser cancelada ou não com a back Key. As opções, de parâmetros são True ou False.
			• setMessage: Define a mensagem de nossa ProgressDialog.
			• setProgressStyle: Define o estilo de nossa ProgressDialog, podendo ser STYLE_HORIZONTAL(Loop Finito) ou STYLE_SPINNER(Loop Infinito).
			• setMax: valor máximo do range da ProgressDialog.
			• show: exibir a ProgressDialog.	
			
5. DatePicker [https://www.tutlane.com/tutorial/android/android-datepicker-with-examples]
	Embora a DatePicker possa ser usada como um widgets independente, ela ocupa muito espaço. 
	É prudente, então, implementá-la dentro de uma caixa de diálogo [DatePickerDialog]
	
6. TimePicker []
	Poderemos alterar a hora e o minuto e escolher entre AM/PM para nosso horário.
	
	DatePickerDialog dialog = new DatePickerDialog(this, mDateSetListener, year, month, day); 
	dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel), 
		new DialogInterface.OnClickListener() { public void onClick(DialogInterface dialog, int which) { if (which == DialogInterface.BUTTON_NEGATIVE) { // Do Stuff } } });
	
	
	