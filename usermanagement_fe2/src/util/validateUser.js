class ValidateUser {
    checkUser(user) {
        if (user.fullName == null || user.dateOfBirth == null || user.address == null) {
            alert("Không được để trống 'họ tên', 'địa chỉ', 'ngày sinh' ");
            return false;
        }
        user.fullName= user.fullName.trim();
        user.address = user.address.trim();

        if (user.fullName == "" || user.dateOfBirth == "" || user.address== "") {
            alert("Không được để trống 'họ tên', 'địa chỉ', 'ngày sinh' ");
            return false;
        }
        
        if (!(user.fullName.length >= 3 && user.fullName.length <= 255)) {
            alert("Họ tên >=3 kí tự và <=255 kí tự ");
            return false;
        }
        if (!(user.address.length >= 3 && user.address.length <= 255)) {
            alert("Địa chỉ >=3 kí tự và <=255 kí tự ");
            return false;
        }
        if (user.dateCreated != "" && user.updateDay != "") {
            if (user.dateCreated > user.updateDay) {
                alert("Ngày cập nhật phải <= ngày tạo");
                return false;
            }
        }

        
        return true;
    }
   



}
export default new ValidateUser()