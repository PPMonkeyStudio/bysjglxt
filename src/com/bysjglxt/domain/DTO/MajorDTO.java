package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_major;
import com.bysjglxt.domain.DO.bysjglxt_section;

/**
 * MajorDTO class
 * 
 * @author JXX
 * @date 2018/3/13
 *
 */
public class MajorDTO {
	private bysjglxt_major bysjglxtMajor;
	private bysjglxt_section bysjglxtSection;

	public bysjglxt_major getBysjglxtMajor() {
		return bysjglxtMajor;
	}

	public void setBysjglxtMajor(bysjglxt_major bysjglxtMajor) {
		this.bysjglxtMajor = bysjglxtMajor;
	}

	public bysjglxt_section getBysjglxtSection() {
		return bysjglxtSection;
	}

	public void setBysjglxtSection(bysjglxt_section bysjglxtSection) {
		this.bysjglxtSection = bysjglxtSection;
	}

	@Override
	public String toString() {
		return "MajorDTO [bysjglxtMajor=" + bysjglxtMajor + ", bysjglxtSection=" + bysjglxtSection + "]";
	}

}
