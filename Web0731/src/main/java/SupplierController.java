import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Supplier;
import model.SupplierDao;

@Path("/suppliers")
public class SupplierController {
	@GET
	@Path("sample")
	public Response save() {
		SupplierDao dao = new SupplierDao();
		boolean result = dao.saveExample();
		if(result) {
			return Response.ok().build();
		}else {
			return Response.noContent().build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		SupplierDao dao = new SupplierDao();
		List<Supplier> l = dao.getAll();
		if(l==null || l.isEmpty()) {
			return Response.noContent().build();
		}else {
			return Response.ok().entity(l).build();
		}
	}
	
	
}
