package Repository;

import java.io.IOException;
import java.util.List;

import Model.PrgState;
import Model_DataStructures.MyList;

public interface IRepository {
	public void logPrgStateExec(PrgState prg) ;
	public void setLogFilePath(String logFilePath);
	public List<PrgState> getPrgList();
	public void setPrgList(List<PrgState> l);
	public MyList<PrgState> getArray();
	public PrgState getById(Integer id);
}
