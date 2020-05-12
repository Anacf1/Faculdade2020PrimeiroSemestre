1. Dimensões
	1.1 Cria o arquivo dimens.xml [na pasta res/values]
		<resources>
			<dimen name="margem_horizontal">100dp</dimen>
			<dimen name="margem_vertical">100dp</dimen>
			<dimen name="textView_tamanhoTexto">12sp</dimen>
			<dimen name="button_tamanhoTexto">12sp</dimen>
		</resources>
	1.2 Faz refrência ao arquivo no layout
		<TextView
			android:layout_width="@dimen/margem_horizontal"
			android:layout_height="@dimen/margem_vertical"
			android:textSize="@dimen/textView_tamanhoTexto"
			android:text="TextView">
		</TextView>
		
2. Cores
	1.1 Cria o arquivo colors.xml [na pasta res/values]
	<resources>
		<color name="colorPrimary">#6200EE</color>
		<color name="colorPrimaryDark">#3700B3</color>
		<color name="colorAccent">#03DAC5</color>
	</resources>
	1.2 Faz refrência ao arquivo no layout
		<Button
			android:background="@color/colorAccent"
			android:textColor="@color/colorPrimaryDark"
       </Button>
	   
3. Strings
	1.1 Cria o arquivo strings.xml [na pasta res/values]
	<resources>
		<string name="app_textoBotaoPrimeiroExercicio">Botão</string>
	</resources>
	1.2 Faz refrência ao arquivo no layout
		 <Button
			android:text="@string/app_textoBotaoPrimeiroExercicio">
		</Button>
		
4. Valores
	4.1 Booleano
		bools.xml
			<resources>
				<bool name="Verdadeiro">true</bool>
			</resources>

		layout.xml
			<Button
				android:clickable="@bool/Verdadeiro">
			</Button>
	
	4.2 Inteiro
		integers.xml
			<resources>
				<integer name="Inteiro">27</integer>
			</resources>
			
	4.3 Arrays de Inteiros
		integers.xml
			<integer-array name="ArrayInteiros">
				<item>1</item>
				<item>3</item>
				<item>9</item>
			</integer-array>
	
	4.4 Array Tipado
		Vetor com tipos		
			<array name="arrayInteiros">
				<item>1</item>
				<item>3</item>
				<item>9</item>
			</array>

			<array name="arrayCores">
				<item>@color/colorAccent</item>
				<item>@color/colorPrimaryDark</item>
				<item>@color/colorPrimary</item>
			</array>

			<array name="arrayStrings">
				<item>Hello</item>
				<item>Wordl</item>
				<item>Android</item>
			</array>
			
5. Estilos [res/values/styles.xml]
	Um estilo em Android corresponde a um conjunto de propriedades que especificam a 
	aparência e o formato para uma View. Este pode especificar propriedades, tais como 
	altura, preenchimento, cor de fonte, tamanho de fonte, cor de fundo e muito mais.
	
	O estilo é definido em um arquivo XML que é separado do XML que especifica o layout.
	
	style.xml
		<style name="ExercicioStyle">
			<item name="android:text">Style</item>
			<item name="android:textSize">45dp</item>
			<item name="android:textColor">@color/colorPrimaryDark</item>
		</style>
		
	layout.xml	
		<TextView
			android:layout_width="match_parent"
			android:layout_height="wrap_content"
			style="@style/ExercicioStyle">
		</TextView>
		
	5.1 Herança de estilo [tag parent]
		<style name="ExercicoHerancaStyle" parent="ExercicioStyle">
			<item name="android:textColor">@color/colorAccent</item>
		</style>
		
		<TextView
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"
			style="@style/ExercicoHerancaStyle">
		</TextView>
		
	5.2 Um tema é um estilo aplicado a uma Activity ou aplicação inteira, ao invés de uma View individual.
		
		*AndroidManifest.xml
		
		5.2.1 Aplicacao inteira
				<application android:theme="@style/AppTheme">       
				
		
		5.2.2. Activity especifica
				<activity android:theme="@style/AppTheme">  
	
	
	
	
	
	
	
	

		
		


