package jdbc;

public class Dao extends BaseDao {

	private static Dao dao;

	static {
		dao = new Dao();
	}

	public static Dao getInstance() {
		return dao;
	}
}
