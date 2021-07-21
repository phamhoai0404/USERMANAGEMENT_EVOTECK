class Authorization {
    isAdmin(account) {
        if (account != null) {
            let roles = account.roles;
            for (let i = 0; i < roles.length; i++) {
                if (roles[i] == "ROLE_ADMIN")
                    return true;
            }
        }
        return false;

    }
}
export default new Authorization()