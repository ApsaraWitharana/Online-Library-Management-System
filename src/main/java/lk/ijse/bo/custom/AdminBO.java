package lk.ijse.bo.custom;

public interface AdminBO {
    boolean updateAdminName(String text, String name);

    boolean deleteAdmin(String name);

    boolean updatePassword(String text, String name);

}
