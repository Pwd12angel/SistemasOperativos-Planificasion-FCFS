package modelo;
//Librerias a utilizar
import java.util.ArrayList; //Array para almacenar los datos
import java.awt.BorderLayout;// vantanas para mostrar los datos
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;
import javax.swing.JFrame;


public class FrameFCFS extends JFrame{ // Realizamps herencia de clase JFrame la cual nos permitira modificarla

    // Variables declaradas para contener datos y posteriormente utilizar en metodos futuros
	private ArrayList<Nodo>list;
	float MediaA;
	float MediaF;

	//Declaramos el metodo constructor
	 public FrameFCFS() {
		 int H1,Ts; // Variables para la hora de llegada y de servicio para los prcesos

		 ArrayList<Nodo> Temp =new ArrayList<>(); // Declaramos el Array para contener a cada uno de los procesos en Tem
         //Introducimos los datos a travez de un JOptionPane ... Nos devolvera los datos en String asi que lo convertiremos
		 int Ps= Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de procesos",
				 "No. de procesos", JOptionPane.QUESTION_MESSAGE));
		 int NumPro=Ps;

		 //Acemos el llenado a travez de un ciclo
		 for(int ct=Ps+1;Ps !=0;Ps--){
			 String mensaje= "Ingresa la Hora de Llegada del "+"Proceso No. "+ (ct-Ps)+";";
             H1=Integer.parseInt(JOptionPane.showInputDialog(null, mensaje, "Horade llegada del Proceso  ",
					 JOptionPane.QUESTION_MESSAGE));
             Ts= Integer.parseInt(JOptionPane.showInputDialog(null, "Y su Tiempo de servicio",
					 	"Tiempo servicio del proceso ", JOptionPane.QUESTION_MESSAGE));

			Temp.add(new Nodo (H1,Ts)); //Los datos se almacenaran a travez del ArrayList Tem
			
		 }
		 boolean on= true;
		 //Array List Final de el programa
		 ArrayList<Nodo> FCFS =new ArrayList<>();
		 //Este es el algoritmo que nos permitira organizar los datos en orden de llegada debido a la naturalesa del algoritmo FCFS
         //Ejecuta el nodo que alla llegado antes
		 while(on) { //Obtendremos el proceso con menor hora de llegada
             // Mini Guarda el menor del arrayList
		     int mini=1000000; // Numeros del proceso en el arrayList final
			 if(!Temp.isEmpty()) { //vaciamos el ArrayList que tienen estos nodos para saber cuando a terminado
				 int cont=0,pos=0;// Cuenta las vueltas
				 for(Nodo nodo: Temp) {// Posicion del arrayList
					 if(nodo.getH_L1()<=mini) {
						 mini=nodo.getH_L1();
						 pos=cont++;
					 }
					 else{cont++;}
				 }
				 FCFS.add(Temp.remove(pos));
			 }
			 else {on=false;}
		 }

		 // Codigo principal que nos ayudara a introducir cada uno de los datos de cada proceso
		 int TimF=0,num=1;
		 //Variables a utilizar
		 float Mediatq=0,Media=0,T_nr,Time,Time2;
		 for(int cont=0;cont<FCFS.size();cont++) { //Nos permitira saber el tamaño del arrayList para poderlo comparar y ejecutarlo
		                                           //En cada uno de los nodos que esten incluidos
			 FCFS.get(cont).setNumP(num++); //Obtenemos el numero de proceso  // Ya estan ordenados de mayor a menor
			 if(cont==0) {
				 TimF=FCFS.get(0).getH_L1()+ TimF+FCFS.get(cont).getT_S();
			 }
			 // Declaramos la variable TimF para ir guardando los valores de tiempo de finalizacion
			 else{TimF=TimF+FCFS.get(cont).getT_R();}
			FCFS.get(cont).setT_F(TimF); // Guardamos el tiempo de finalizacion en setT_F
			FCFS.get(cont).setT_R(TimF-FCFS.get(cont).getH_L1());// Guardamos el tiempo de retorno con el tiempo de finalizacion menos la hora de llegada
                                                                 //Asi optendremos el tiempo de retorno para el proceso en ese momento de ejecucion
			T_nr= (float) FCFS.get(cont).getT_R();
			FCFS.get(cont).setT_NR(T_nr/FCFS.get(cont).getT_S());// Despues guardamos el tiempo de retorno entre el tiempo de servicio
			Time=(float) FCFS.get(cont).getT_R();
			Mediatq=Mediatq+Time; // Por utimo vamos acomulando cada uno de los datos del tiempo de retorno y del tiempo normalizado de retorno para
                                  // Obtener las medias de cada uno
			Time2=FCFS.get(cont).getT_NR();
			Media = Media+Time2;

			// EL CODIGO SE EJECUTARA HASTA QUE YA NO QUEDE NINGUN PROCESO SIN INCLUIR SUS DATOS
		 }
		 //Guardaremos la media de cada uno de los totales de tiempo de finalizacion y tiempo de retorno par acada uno de los prcesos
		 MediaA=Mediatq/NumPro;
		 MediaF=Media/NumPro;
		 list=FCFS;//Pasaremos FCFS A List que nos permitira usarlo para imprimir

         //Comenzamos con el FRAME  Introducimos el titulo a la ventana
		 setTitle("algoritmo de Planificacion: FCFS" +"(First Como First Serced)");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setBounds(100,100,800,600);

		 //Reañizaremos una matriz de objeto para realizar la tabla que se mostrara en la ventana
		int Ncol=NumPro+1;// colubnas
		Object datos[][]=new Object[5][Ncol];
		for(int fil=0;fil<5;fil++) {
			switch(fil){
			    //Introcucimos los datos a las columnas
			case 0:
				datos[fil][0]="Hora de Llegada";
				break;
			case 1:
				datos[fil][0]="Tiempo servicio";
				break;
			case 2:
				datos[fil][0]="Tiempo FZN";
			break;
			case 3:
				datos[fil][0]="Tiempo Retorno";
				break;
			case 4:
				datos[fil][0]="Tiempo NR";
				break;
			}
			int con=0;
			//Declaramos el For para incluir cada uno de los proceos
            //Introcucimos los datos que ya teniamos en la tabla
			for(int col =1;col<Ncol;col++,con++) {
				switch(fil) {
				case 0:
					datos[fil][col]=FCFS.get(con).getH_L1();
					break;
				case 1:
					datos[fil][col]=FCFS.get(con).getT_S();
					break;
				case 2:
					datos[fil][col]=FCFS.get(con).getT_F();
				break;
				case 3:
					datos[fil][col]=FCFS.get(con).getT_R();
					break;
				case 4:
					datos[fil][col]=FCFS.get(con).getT_NR();
					break;
				}
			}
		}
		int in=0;

		// Creamos otro objeto de la columna, es un Array que nos permitira guardar el titulo de las columnas para la tabla
         //Declaramos el objeto para incluirselo a la tabla
    Object columnas[]=new Object[Ncol];
    for(int i=0;i<Ncol;i++) {
    	if(i==0) {columnas[i]="Numero de proceso: ";}
    	else {
    		columnas[i]=FCFS.get(in).getNumP();
    		in++;
    	}
    }
		
    JTable tbl=new JTable(datos,columnas);//Con JTable metemos los datos a la tabla, Los datos y las columnas

    JScrollPane panel = new JScrollPane(tbl);
    getContentPane().add(panel,BorderLayout.CENTER);
    setVisible(true);
    
	 }

	 //Declaramos el metodo pain de la libreria Graphics con el cual dibujaremos los procesos en tiempo real
	 public void paint(Graphics g) {
		 super.paint(g);
		 int sizelist =list.size();
		 int size=list.get(sizelist-1).getT_F();

		 //Primero dibujamos la media de tiempo de retorno
		 g.setColor(Color.BLACK);
		 g.drawString("Media Tq: ",550,160);
		 String Tq=Float.toString(MediaA);
		 g.drawString(Tq,640, 160);

		 //Despues dibujamos la media normalizada de retorno
		 g.drawString("Media Tq / Ts ", 550, 190);
		 String Tqs=Float.toString(MediaF);
		 g.drawString(Tqs, 640, 190);

		 // Dibujamos la tabla La dibujaremos a traves de lineas y numeros impresos
		 g.drawString("FCFS / Procesos", 100, 200);
		 int total=size*22;
		 g.drawLine(50, 249, (total+50),249); //Dibujaremos por medio de pixeles, Cantidad de pixeles
                                                          // Linea vertical y una Horizontal
                                                          // Las cuales representarian la cantidad de cuantums y la
		                                                  // calntidad de procesos en ejecucuion
		 sizelist*=22;
		 g.drawLine(50, 250, 50, (sizelist+250));

		 // Dibujamos los numeros que representaran los quantums y los procesos
		 int lim=21;
		 for(int ps=0;total>0;total-=22,ps++) {
			 String proc= Integer.toString(ps);
			 if(ps==0)g.drawString(proc, 50, 248);
			 else {
				 g.drawString(proc, (lim+50), 248);
				 lim+=22;
			 }
		 }

		 //Dibujamos los procesos
		 int posY=250,tFin=0;
		 int iniq,inip;

		 //Utilizamos el for para inicializar donde queremos que detengan nuestros procesos inicio
         // El inicio sera a los 50px despues y cada uno de los quantus tendren el tamaño de 20 * 20 px con una separacion de 2px
		 for(Nodo nodo: list) {
			 if(nodo.getNumP()==1) {
				 iniq=nodo.getH_L1()*22;
				 inip=0;
				 tFin=nodo.getT_F();
				 
			 }
			 else {
				 iniq=tFin*22;
				 inip=(nodo.getNumP()-1)*22;
				 tFin=nodo.getT_F();
				 
			 }
			 int Tims=nodo.getT_S();

			 //Utilizamos 3 varibales R, G, B, que son random para el cambio de color de cada uno de los procesos
			 int R=(int)(Math.random()*256);
			 int G=(int)(Math.random()*256);
			 int B=(int)(Math.random()*256);
			 
			 Color randomColor =new Color(R,G,B);
			 
			 int posX=50;
			 g.setColor(randomColor); //Lo utilizamos para poner el nuevo color

			 for(;Tims>0;Tims--) { //For nos imprimira cada uno de los procesos
				 detenerElTiempo(); // detener tiempo Nos permitira relentizar la ejecucion en tiepo real para que no se vea tan apresurada
				 g.fillRect(posX+iniq, (posY+inip), 20, 20);
				 posX+=22;
			 }
		 }
	 }
	 // Metodo para relentizar
	private void detenerElTiempo() {
		try {
			Thread.sleep(500);
		}catch(InterruptedException e){
			
		}
		
	}
	 
	 }
	
