package server;

import java.util.ArrayList;

import jdbc.MemberDAO;
import jdbc.MemberDTO;

public class Member {
	private ArrayList<MemberDTO> mList;
	private MemberDAO mDao;
	private MemberDTO mDto;

	public Member() {
		mDao = MemberDAO.getInstance();
	}

	public int login(String id, String pw) {
		if (iddup(id)) {
			return 1;
		} else if (!mDto.getPassword().equals(pw)) {
			return 2;
		} else {
			return 3;
		}
	}

	public void signUp(String id, String pw) {
		MemberDTO mDto = new MemberDTO();
		mDto.setId(id);
		mDto.setPassword(pw);
		if (mDao.insert(mDto) == 1)
			System.out.println("회원가입 성공");
	}

	public boolean iddup(String id) {
		mList = mDao.selectAll();
		if (mList.size() < 0)
			return true;
		else {
			for (int i = 0; i < mList.size(); i++) {
				if (mList.get(i).getId().equals(id)) {
					this.mDto = mList.get(i);
					return false;
				}
			}
			return true;
		}
	}

}
