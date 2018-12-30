package com.bysjglxt.domain.DO;

public class bysjglxt_defence {

	private String defence_id;
	private String defence_student;
	private String defence_record;
	private String defence_leader_comment;
	private int defence_grade_writing = 10;
	private int defence_grade_normalization = 6;
	private int defence_grade_complete = 4;
	private int defence_grade_technology = 14;
	private int defence_grade_practicability = 16;
	private int defence_grade_appearance = 5;
	private int defence_grade_statement = 20;
	private int defence_grade_answer = 25;
	private int defence_chenshu_time = 0;
	private int defence_tiwen_time = 0;
	private int defence_grade_defence;
	private double defence_grade_evaluate_tutor;
	private double defence_grade_evaluate_review;
	private int defence_total;
	private String defence_finally;
	private String defence_gmt_create;
	private String defence_gmt_modified;

	public String getDefence_id() {
		return defence_id;
	}

	public void setDefence_id(String defence_id) {
		this.defence_id = defence_id;
	}

	public int getDefence_chenshu_time() {
		return defence_chenshu_time;
	}

	public void setDefence_chenshu_time(int defence_chenshu_time) {
		this.defence_chenshu_time = defence_chenshu_time;
	}

	public int getDefence_tiwen_time() {
		return defence_tiwen_time;
	}

	public void setDefence_tiwen_time(int defence_tiwen_time) {
		this.defence_tiwen_time = defence_tiwen_time;
	}

	public String getDefence_student() {
		return defence_student;
	}

	public void setDefence_student(String defence_student) {
		this.defence_student = defence_student;
	}

	public String getDefence_record() {
		return defence_record;
	}

	public void setDefence_record(String defence_record) {
		this.defence_record = defence_record;
	}

	public String getDefence_leader_comment() {
		return defence_leader_comment;
	}

	public void setDefence_leader_comment(String defence_leader_comment) {
		this.defence_leader_comment = defence_leader_comment;
	}

	public int getDefence_grade_writing() {
		return defence_grade_writing;
	}

	public void setDefence_grade_writing(int defence_grade_writing) {
		this.defence_grade_writing = defence_grade_writing;
	}

	public int getDefence_grade_normalization() {
		return defence_grade_normalization;
	}

	public void setDefence_grade_normalization(int defence_grade_normalization) {
		this.defence_grade_normalization = defence_grade_normalization;
	}

	public int getDefence_grade_complete() {
		return defence_grade_complete;
	}

	public void setDefence_grade_complete(int defence_grade_complete) {
		this.defence_grade_complete = defence_grade_complete;
	}

	public int getDefence_grade_technology() {
		return defence_grade_technology;
	}

	public void setDefence_grade_technology(int defence_grade_technology) {
		this.defence_grade_technology = defence_grade_technology;
	}

	public int getDefence_grade_practicability() {
		return defence_grade_practicability;
	}

	public void setDefence_grade_practicability(int defence_grade_practicability) {
		this.defence_grade_practicability = defence_grade_practicability;
	}

	public int getDefence_grade_appearance() {
		return defence_grade_appearance;
	}

	public void setDefence_grade_appearance(int defence_grade_appearance) {
		this.defence_grade_appearance = defence_grade_appearance;
	}

	public int getDefence_grade_statement() {
		return defence_grade_statement;
	}

	public void setDefence_grade_statement(int defence_grade_statement) {
		this.defence_grade_statement = defence_grade_statement;
	}

	public int getDefence_grade_answer() {
		return defence_grade_answer;
	}

	public void setDefence_grade_answer(int defence_grade_answer) {
		this.defence_grade_answer = defence_grade_answer;
	}

	public int getDefence_grade_defence() {
		return defence_grade_defence;
	}

	public void setDefence_grade_defence(int defence_grade_defence) {
		this.defence_grade_defence = defence_grade_defence;
	}

	public double getDefence_grade_evaluate_tutor() {
		return defence_grade_evaluate_tutor;
	}

	public void setDefence_grade_evaluate_tutor(double defence_grade_evaluate_tutor) {
		this.defence_grade_evaluate_tutor = defence_grade_evaluate_tutor;
	}

	public double getDefence_grade_evaluate_review() {
		return defence_grade_evaluate_review;
	}

	public void setDefence_grade_evaluate_review(double defence_grade_evaluate_review) {
		this.defence_grade_evaluate_review = defence_grade_evaluate_review;
	}

	public int getDefence_total() {
		return defence_total;
	}

	public void setDefence_total(int defence_total) {
		this.defence_total = defence_total;
	}

	public String getDefence_finally() {
		return defence_finally;
	}

	public void setDefence_finally(String defence_finally) {
		this.defence_finally = defence_finally;
	}

	public String getDefence_gmt_create() {
		return defence_gmt_create;
	}

	public void setDefence_gmt_create(String defence_gmt_create) {
		this.defence_gmt_create = defence_gmt_create;
	}

	public String getDefence_gmt_modified() {
		return defence_gmt_modified;
	}

	public void setDefence_gmt_modified(String defence_gmt_modified) {
		this.defence_gmt_modified = defence_gmt_modified;
	}

	@Override
	public String toString() {
		return "bysjglxt_defence [defence_id=" + defence_id + ", defence_student=" + defence_student
				+ ", defence_record=" + defence_record + ", defence_leader_comment=" + defence_leader_comment
				+ ", defence_grade_writing=" + defence_grade_writing + ", defence_grade_normalization="
				+ defence_grade_normalization + ", defence_grade_complete=" + defence_grade_complete
				+ ", defence_grade_technology=" + defence_grade_technology + ", defence_grade_practicability="
				+ defence_grade_practicability + ", defence_grade_appearance=" + defence_grade_appearance
				+ ", defence_grade_statement=" + defence_grade_statement + ", defence_grade_answer="
				+ defence_grade_answer + ", defence_chenshu_time=" + defence_chenshu_time + ", defence_tiwen_time="
				+ defence_tiwen_time + ", defence_grade_defence=" + defence_grade_defence
				+ ", defence_grade_evaluate_tutor=" + defence_grade_evaluate_tutor + ", defence_grade_evaluate_review="
				+ defence_grade_evaluate_review + ", defence_total=" + defence_total + ", defence_finally="
				+ defence_finally + ", defence_gmt_create=" + defence_gmt_create + ", defence_gmt_modified="
				+ defence_gmt_modified + "]";
	}

}
