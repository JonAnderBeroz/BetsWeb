import businessLogic.BLFacade;
import bussinessLogic.BLFacadeImplementation;

public class FacadeBean {
	private static FacadeBean singleton = new FacadeBean( );
	private static BLFacadeImplementation facadeInterface;
	FacadeBean(){
		try { facadeInterface= new BLFacadeImplementation(); }
		catch (Exception e) {
			System.out.println("FacadeBean: negozioaren logika sortzean errorea: "+e.getMessage());
		}}
	public static BLFacadeImplementation getBusinessLogic( ) {
		return facadeInterface;
	}
}
