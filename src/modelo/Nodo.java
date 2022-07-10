package modelo;

public class Nodo {
	private int NumP;// numero de procesos
	private int H_L1;// Hora de llegada del proceso
	private int T_S;// Tiempo de servicio del proceso
	
	// Despues de calcular
	private int T_F; //Tiempo de finalizacion
	private int T_R; //Tiempo de retorno
	private float T_NR;// Tiempo normalizado de retorno

	public Nodo(int H1, int Ts) { //Mandamos la hora de llegada y el tiempo de servicio
		H_L1=H1;
		T_S=Ts;
	}
	// creamos todos los gets y set para ingreso de informacion
	public int getNumP() {
		return NumP;
	}
	public int getT_R() {
		return T_R;
	}
	public void setT_R(int t_R) {
		T_R = t_R;
	}
	public int getT_S() {
		return T_S;
	}
	public void setT_S(int t_S) {
		T_S = t_S;
	}
	public void setNumP(int pos) {
		NumP = pos;
	}
	public int getT_F() {
		return T_F;
	}
	public void setT_F(int val) {
		T_F = val;
	}
	public float getT_NR() {
		return T_NR;
	}
	public void setT_NR(float val) {
		T_NR = val;
	}
	public int getH_L1() {
		return H_L1;
	}
	public void setH_L1(int h_L1) {
		H_L1 = h_L1;
	}
	
	
	

}
