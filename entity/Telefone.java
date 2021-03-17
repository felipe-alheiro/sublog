package entity;

public class Telefone{
	long id_telefone;
	String ddd;
	String ddi;
	String numero_telefone;
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getDdi() {
		return ddi;
	}
	public void setDdi(String ddi) {
		this.ddi = ddi;
	}
	public String getNumero_telefone() {
		return numero_telefone;
	}
	public void setNumero_telefone(String numero_telefone) {
		this.numero_telefone = numero_telefone;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
		result = prime * result + ((ddi == null) ? 0 : ddi.hashCode());
		result = prime * result + ((numero_telefone == null) ? 0 : numero_telefone.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		if (ddd == null) {
			if (other.ddd != null)
				return false;
		} else if (!ddd.equals(other.ddd))
			return false;
		if (ddi == null) {
			if (other.ddi != null)
				return false;
		} else if (!ddi.equals(other.ddi))
			return false;
		if (numero_telefone == null) {
			if (other.numero_telefone != null)
				return false;
		} else if (!numero_telefone.equals(other.numero_telefone))
			return false;
		return true;
	}
	// uso da classe StringBuilder que o Lu�z apresentou
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[ +").append(ddd).append(ddi).append(numero_telefone).append(" ]");
		return str.toString();
	}
	
	
}