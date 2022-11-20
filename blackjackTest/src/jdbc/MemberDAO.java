package jdbc;

import java.util.ArrayList;

public class MemberDAO extends SuperDAO {
	public MemberDAO() {
		super();
	}

	public static MemberDAO getInstance() {
		return LazyHolder.IT;
	}

	private static class LazyHolder {
		private static final MemberDAO IT = new MemberDAO();
	}

	public void moneyUpdate(MemberDTO mDto) {
		if (getConn() != null) {
			try {
				String sql = "update membertest set money=? where id=?";
				setPstmt(conn.prepareStatement(sql));
				getPstmt().setInt(1, mDto.getMoney());
				getPstmt().setString(2, mDto.getId());
				getPstmt().executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					conn = null;
				if (getPstmt() != null)
					setPstmt(null);
			}
		}
	}

	public ArrayList<MemberDTO> selectAll() {
		ArrayList<MemberDTO> mList = new ArrayList<>();
		if (getConn() != null) {
			try {
				String sql = "select * from membertest";
				setStmt(conn.createStatement());
				setRs(getStmt().executeQuery(sql));
				while (getRs().next()) {
					MemberDTO mDto = new MemberDTO();
					mDto.setId(getRs().getString("id"));
					mDto.setPassword(getRs().getString("password"));
					mDto.setMoney(getRs().getInt("money"));
					mList.add(mDto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					conn = null;
				if (getStmt() != null)
					setStmt(null);
			}
		}
		return mList;
	}

	public MemberDTO selectOne(String id) {
		MemberDTO mDto = new MemberDTO();
		if (getConn() != null) {
			try {
				String sql = "select * from membertest where id = ? ";
				setPstmt(conn.prepareStatement(sql));
				getPstmt().setString(1, id);
				setRs(getPstmt().executeQuery());
				if (getRs().next()) {
					mDto.setId(getRs().getString("id"));
					mDto.setPassword(getRs().getString("password"));
					mDto.setMoney(getRs().getInt("money"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					conn = null;
				if (getPstmt() != null)
					setPstmt(null);
			}
		}
		return mDto;
	}

	public int insert(MemberDTO m) {
		int rs = -1;
		if (getConn() != null) {
			try {
				String sql = "insert into membertest (id, password) values (?,?)";
				setPstmt(conn.prepareStatement(sql));
				getPstmt().setString(1, m.getId());
				getPstmt().setString(2, m.getPassword());
//				getPstmt().setInt(3, m.getMoney());
				rs = getPstmt().executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					conn = null;
				if (getPstmt() != null)
					setPstmt(null);
			}
		}
		return rs;
	}
}
