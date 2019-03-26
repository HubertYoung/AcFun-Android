package com.hubertyoung.component_gridpasswordview.gridpasswordview;

interface PasswordView {

    //void setError(String error);

    String getPassWord();

    void clearPassword();

    void setPassword( String password );

    void setPasswordVisibility( boolean visible );

    void togglePasswordVisibility();

    void setOnPasswordChangedListener( GridPasswordView.OnPasswordChangedListener listener );

    void setPasswordType( PasswordType passwordType );
}
