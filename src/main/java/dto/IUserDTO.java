package dto;

import java.util.List;

public interface IUserDTO {
	int getUserId();

	void setUserId(int userId);

	String getUserName();

	void setUserName(String userName);

	void setAdmin(boolean admin);

	boolean isAdmin();

	void setLabo(boolean labo);

	boolean isLabo();

	void setPLeader(boolean pLeader);

	boolean isPLeader();

	void setPharma(boolean pharma);

	boolean isPharma();


}
