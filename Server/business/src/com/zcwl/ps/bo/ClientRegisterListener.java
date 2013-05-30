package com.zcwl.ps.bo;

import org.springframework.stereotype.Service;

import com.xpush.android.xptp.RegisterParser.RegisterListener;
import com.xpush.android.xptp.dto.Register;

@Service
public class ClientRegisterListener implements RegisterListener{

	@Override
	public void register(Register register) {
		System.out.println("申请注册用户："+register.getDeviceId());
	}

}
