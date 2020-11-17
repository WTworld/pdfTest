package com.u_anywhere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u_anywhere.mappers.TestPrintwavenoMapper;
import com.u_anywhere.model.TestPrintwaveno;
@Service
public class TestPrintwavenoService {
	@Autowired
	TestPrintwavenoMapper testPrintwavenoMapper;
	
	public int saveWaveCount(String waveNo,int count) {
		TestPrintwaveno wave = new TestPrintwaveno();
		wave.setWaveno(waveNo);
		wave.setPrintcount(count);
		TestPrintwaveno old = testPrintwavenoMapper.selectByPrimaryKey(wave);
		if (old == null) {
			return testPrintwavenoMapper.insertSelective(wave);
		}
		return testPrintwavenoMapper.updateByPrimaryKey(wave);
	}

}
