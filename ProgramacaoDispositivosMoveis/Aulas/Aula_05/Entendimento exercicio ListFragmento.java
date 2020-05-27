1. Eu tenho um .xml [layout_principal.xml], com dois 'fragmentos':
	1.1 Lista 
		<fragment
        android:id="@+id/listaFragmento"
        class="br.estacio.com.listviewfragment.ListaActivity" -> Classe .java que presenta o fragmento
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="0.5"></fragment>
		
	1.2 Texto
		<fragment
        android:id="@+id/textoFragmento"
        class="br.estacio.com.listviewfragment.TextoActivity" -> Classe .java que presenta o fragmento
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="0.5"></fragment>

2. Eu tenho um .java [PrincipalActivity.java] normal

3. 'Cada' framento [Lista, Texto] possui:
	3.1 Um layout .xml 	
	
		3.1.1 Lista [layout_lista.xml]
			<ListView
			android:id="@android:id/list"
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"></ListView>

		3.1.2 Texto	[layout_texto.xml]
			<TextView
			android:id="@+id/texto"
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"></TextView>
			
	3.2 Uma Activity .java 
	
		3.2.1 Texto [TextoActivity.java][layout_texto.xml -> TextoActivity.java]
		
		/*Estende 'Fragment', porque:
				1. Representa um fragmento*/				
		public class TextoActivity extends Fragment {
			
			TextView textView;

			/*Como sendo um fragmento, temos o método 'onCreateView' ao invés de 'onCreate (Actvity)'*/
			public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
				View view = inflater.inflate(R.layout.layout_texto, container, false);

				/*Seria o findById*/
				textView = view.findViewById(R.id.texto);
				return view;
			}

			/*Método criado somente para setar algum texto na TextView*/
			public void change(String texto){
				textView.setText(texto);
			}
		} 
		 
	
		3.2.2 Lista [ListaActivity.java][layout_lista.xml -> ListaActivity.java]
		
		/*Estende 'ListFragment', porque:
				1. Representa um fragmento 
				2. É uma lista*/
		public class ListaActivity extends ListFragment { 
    
			/*Como sendo um fragmento, temos o método 'onCreateView' ao invés de 'onCreate (Actvity)'*/
			public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
				
				View view = inflater.inflate(R.layout.layout_lista, container, false);

				/*Criando e inicializando a lista normalmente*/
				String [] servicos = new String [] {"Cabeleireiro", "Depilacao", "Manicure"};
				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, servicos);
				setListAdapter(arrayAdapter);
				return view;
			}
			
			/*Aqui vou definir o que acontece ao clicar em cada um dos itens da lista*/
			public void onListItemClick(ListView list, View view, int posicaoSelecionada, long id){
				super.onListItemClick(list, view, posicaoSelecionada, id);

				/*Vincula o fragmento que está no layout principal 'textoFragmento', com a classe java 'TextoActivity',
				* uma vez em que 'ambos' são fragmentos*/
				TextoActivity textoFragment = (TextoActivity) getFragmentManager().findFragmentById(R.id.textoFragmento);

				/*Aqui eu determino o que vai aparecer no fragmento de texto*/
				textoFragment.change(servicos[posicaoSelecionada] + " : " + descricao[posicaoSelecionada] );

				/*ALterando a cor da lista*/
				getListView().setSelector(android.R.color.holo_orange_light);

		}