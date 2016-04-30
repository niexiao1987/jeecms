package com.jeecms.cms.statistic.rankreport;

import java.io.Serializable;

public class RankReport implements Serializable{
	private static final long serialVersionUID = 1L;
	private	String   year;              
	private	Integer  departmentId;   
	private String   departmentName;
	private	String   departmentPattern;
	private	Long     januaryTotal;     
	private	Float    januaryPer ;  
	private Integer  januaryTotalRank;
	private Integer  januaryPerRank;
	private	Long     februaryTotal;    
	private	Float    februaryPer;
	private Integer  februaryTotalRank; 
	private Integer  februaryPerRank;
	private	Long     marchTotal ;      
	private	Float    marchPer ; 
	private Integer  marchTotalRank;
	private Integer  marchPerRank;
	private	Long     aprilTotal   ;    
	private	Float    aprilPer;  
	private Integer  aprilTotalRank;
	private Integer  aprilPerRank;
	private	Long     mayTotal;         
	private	Float    mayPer ;  
	private Integer  mayTotalRank;
	private Integer  mayPerRank;
	private	Long     juneTotal;        
	private	Float    junePer;   
	private Integer  juneTotalRank;
	private Integer  junePerRank;
	private	Long     julyTotal;        
	private	Float    julyPer;   
	private Integer  julyTotalRank;
	private Integer  julyPerRank;
	private	Long     augustTotal ;     
	private	Float    augustPer;  
	private Integer  augustTotalRank;
	private Integer  augustPerRank;
	private	Long     septemberTotal;   
	private	Float    septemberPer ;  
	private Integer  septemberTotalRank;
	private Integer  septemberPerRank;
	private	Long     octoberTotal;     
	private	Float    octoberPer ;  
	private Integer  octoberTotalRank;
	private Integer  octoberPerRank;
	private	Long     novemberTotal;    
	private	Float    novemberPer ; 
	private Integer  novemberTotalRank;
	private Integer  novemberPerRank;
	private	Long     decemberTotal ;   
	private	Float    decemberPer ;
	private Integer  decemberTotalRank;
	private Integer  decemberPerRank;
	private	Long     yearTotal ;       
	private	Float    yearPer ;
	private Integer  yearTotalRank;
	private Integer  yearPerRank;
	
	
	public Integer getJanuaryTotalRank() {
		return januaryTotalRank;
	}
	public void setJanuaryTotalRank(Integer januaryTotalRank) {
		this.januaryTotalRank = januaryTotalRank;
	}
	public Integer getJanuaryPerRank() {
		return januaryPerRank;
	}
	public void setJanuaryPerRank(Integer januaryPerRank) {
		this.januaryPerRank = januaryPerRank;
	}
	public Integer getFebruaryTotalRank() {
		return februaryTotalRank;
	}
	public void setFebruaryTotalRank(Integer februaryTotalRank) {
		this.februaryTotalRank = februaryTotalRank;
	}
	public Integer getFebruaryPerRank() {
		return februaryPerRank;
	}
	public void setFebruaryPerRank(Integer februaryPerRank) {
		this.februaryPerRank = februaryPerRank;
	}
	public Integer getMarchTotalRank() {
		return marchTotalRank;
	}
	public void setMarchTotalRank(Integer marchTotalRank) {
		this.marchTotalRank = marchTotalRank;
	}
	public Integer getMarchPerRank() {
		return marchPerRank;
	}
	public void setMarchPerRank(Integer marchPerRank) {
		this.marchPerRank = marchPerRank;
	}
	public Integer getAprilTotalRank() {
		return aprilTotalRank;
	}
	public void setAprilTotalRank(Integer aprilTotalRank) {
		this.aprilTotalRank = aprilTotalRank;
	}
	public Integer getAprilPerRank() {
		return aprilPerRank;
	}
	public void setAprilPerRank(Integer aprilPerRank) {
		this.aprilPerRank = aprilPerRank;
	}
	public Integer getMayTotalRank() {
		return mayTotalRank;
	}
	public void setMayTotalRank(Integer mayTotalRank) {
		this.mayTotalRank = mayTotalRank;
	}
	public Integer getMayPerRank() {
		return mayPerRank;
	}
	public void setMayPerRank(Integer mayPerRank) {
		this.mayPerRank = mayPerRank;
	}
	public Integer getJuneTotalRank() {
		return juneTotalRank;
	}
	public void setJuneTotalRank(Integer juneTotalRank) {
		this.juneTotalRank = juneTotalRank;
	}
	public Integer getJunePerRank() {
		return junePerRank;
	}
	public void setJunePerRank(Integer junePerRank) {
		this.junePerRank = junePerRank;
	}
	public Integer getJulyTotalRank() {
		return julyTotalRank;
	}
	public void setJulyTotalRank(Integer julyTotalRank) {
		this.julyTotalRank = julyTotalRank;
	}
	public Integer getJulyPerRank() {
		return julyPerRank;
	}
	public void setJulyPerRank(Integer julyPerRank) {
		this.julyPerRank = julyPerRank;
	}
	public Integer getAugustTotalRank() {
		return augustTotalRank;
	}
	public void setAugustTotalRank(Integer augustTotalRank) {
		this.augustTotalRank = augustTotalRank;
	}
	public Integer getAugustPerRank() {
		return augustPerRank;
	}
	public void setAugustPerRank(Integer augustPerRank) {
		this.augustPerRank = augustPerRank;
	}
	public Integer getSeptemberTotalRank() {
		return septemberTotalRank;
	}
	public void setSeptemberTotalRank(Integer septemberTotalRank) {
		this.septemberTotalRank = septemberTotalRank;
	}
	public Integer getSeptemberPerRank() {
		return septemberPerRank;
	}
	public void setSeptemberPerRank(Integer septemberPerRank) {
		this.septemberPerRank = septemberPerRank;
	}
	public Integer getOctoberTotalRank() {
		return octoberTotalRank;
	}
	public void setOctoberTotalRank(Integer octoberTotalRank) {
		this.octoberTotalRank = octoberTotalRank;
	}
	public Integer getOctoberPerRank() {
		return octoberPerRank;
	}
	public void setOctoberPerRank(Integer octoberPerRank) {
		this.octoberPerRank = octoberPerRank;
	}
	public Integer getNovemberTotalRank() {
		return novemberTotalRank;
	}
	public void setNovemberTotalRank(Integer novemberTotalRank) {
		this.novemberTotalRank = novemberTotalRank;
	}
	public Integer getNovemberPerRank() {
		return novemberPerRank;
	}
	public void setNovemberPerRank(Integer novemberPerRank) {
		this.novemberPerRank = novemberPerRank;
	}
	public Integer getDecemberTotalRank() {
		return decemberTotalRank;
	}
	public void setDecemberTotalRank(Integer decemberTotalRank) {
		this.decemberTotalRank = decemberTotalRank;
	}
	public Integer getDecemberPerRank() {
		return decemberPerRank;
	}
	public void setDecemberPerRank(Integer decemberPerRank) {
		this.decemberPerRank = decemberPerRank;
	}
	public Integer getYearTotalRank() {
		return yearTotalRank;
	}
	public void setYearTotalRank(Integer yearTotalRank) {
		this.yearTotalRank = yearTotalRank;
	}
	public Integer getYearPerRank() {
		return yearPerRank;
	}
	public void setYearPerRank(Integer yearPerRank) {
		this.yearPerRank = yearPerRank;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentPattern() {
		return departmentPattern;
	}
	public void setDepartmentPattern(String departmentPattern) {
		this.departmentPattern = departmentPattern;
	}
	public Long getJanuaryTotal() {
		return januaryTotal;
	}
	public void setJanuaryTotal(Long januaryTotal) {
		this.januaryTotal = januaryTotal;
	}
	public Float getJanuaryPer() {
		return januaryPer;
	}
	public void setJanuaryPer(Float januaryPer) {
		this.januaryPer = januaryPer;
	}
	public Long getFebruaryTotal() {
		return februaryTotal;
	}
	public void setFebruaryTotal(Long februaryTotal) {
		this.februaryTotal = februaryTotal;
	}
	public Float getFebruaryPer() {
		return februaryPer;
	}
	public void setFebruaryPer(Float februaryPer) {
		this.februaryPer = februaryPer;
	}
	public Long getMarchTotal() {
		return marchTotal;
	}
	public void setMarchTotal(Long marchTotal) {
		this.marchTotal = marchTotal;
	}
	public Float getMarchPer() {
		return marchPer;
	}
	public void setMarchPer(Float marchPer) {
		this.marchPer = marchPer;
	}
	public Long getAprilTotal() {
		return aprilTotal;
	}
	public void setAprilTotal(Long aprilTotal) {
		this.aprilTotal = aprilTotal;
	}
	public Float getAprilPer() {
		return aprilPer;
	}
	public void setAprilPer(Float aprilPer) {
		this.aprilPer = aprilPer;
	}
	public Long getMayTotal() {
		return mayTotal;
	}
	public void setMayTotal(Long mayTotal) {
		this.mayTotal = mayTotal;
	}
	public Float getMayPer() {
		return mayPer;
	}
	public void setMayPer(Float mayPer) {
		this.mayPer = mayPer;
	}
	public Long getJuneTotal() {
		return juneTotal;
	}
	public void setJuneTotal(Long juneTotal) {
		this.juneTotal = juneTotal;
	}
	public Float getJunePer() {
		return junePer;
	}
	public void setJunePer(Float junePer) {
		this.junePer = junePer;
	}
	public Long getJulyTotal() {
		return julyTotal;
	}
	public void setJulyTotal(Long julyTotal) {
		this.julyTotal = julyTotal;
	}
	public Float getJulyPer() {
		return julyPer;
	}
	public void setJulyPer(Float julyPer) {
		this.julyPer = julyPer;
	}
	public Long getAugustTotal() {
		return augustTotal;
	}
	public void setAugustTotal(Long augustTotal) {
		this.augustTotal = augustTotal;
	}
	public Float getAugustPer() {
		return augustPer;
	}
	public void setAugustPer(Float augustPer) {
		this.augustPer = augustPer;
	}
	public Long getSeptemberTotal() {
		return septemberTotal;
	}
	public void setSeptemberTotal(Long septemberTotal) {
		this.septemberTotal = septemberTotal;
	}
	public Float getSeptemberPer() {
		return septemberPer;
	}
	public void setSeptemberPer(Float septemberPer) {
		this.septemberPer = septemberPer;
	}
	public Long getOctoberTotal() {
		return octoberTotal;
	}
	public void setOctoberTotal(Long octoberTotal) {
		this.octoberTotal = octoberTotal;
	}
	public Float getOctoberPer() {
		return octoberPer;
	}
	public void setOctoberPer(Float octoberPer) {
		this.octoberPer = octoberPer;
	}
	public Long getNovemberTotal() {
		return novemberTotal;
	}
	public void setNovemberTotal(Long novemberTotal) {
		this.novemberTotal = novemberTotal;
	}
	public Float getNovemberPer() {
		return novemberPer;
	}
	public void setNovemberPer(Float novemberPer) {
		this.novemberPer = novemberPer;
	}
	public Long getDecemberTotal() {
		return decemberTotal;
	}
	public void setDecemberTotal(Long decemberTotal) {
		this.decemberTotal = decemberTotal;
	}
	public Float getDecemberPer() {
		return decemberPer;
	}
	public void setDecemberPer(Float decemberPer) {
		this.decemberPer = decemberPer;
	}
	public Long getYearTotal() {
		return yearTotal;
	}
	public void setYearTotal(Long yearTotal) {
		this.yearTotal = yearTotal;
	}
	public Float getYearPer() {
		return yearPer;
	}
	public void setYearPer(Float yearPer) {
		this.yearPer = yearPer;
	}
	@Override
	public String toString() {
		return "RankReport [year=" + year + ", departmentId=" + departmentId
				+ ", departmentName=" + departmentName + ", departmentPattern="
				+ departmentPattern + ", januaryTotal=" + januaryTotal
				+ ", januaryPer=" + januaryPer + ", februaryTotal="
				+ februaryTotal + ", februaryPer=" + februaryPer
				+ ", marchTotal=" + marchTotal + ", marchPper=" + marchPer
				+ ", aprilTotal=" + aprilTotal + ", aprilPer=" + aprilPer
				+ ", mayTotal=" + mayTotal + ", mayPer=" + mayPer
				+ ", juneTotal=" + juneTotal + ", junePer=" + junePer
				+ ", julyTotal=" + julyTotal + ", julyPer=" + julyPer
				+ ", augustTotal=" + augustTotal + ", augustPer=" + augustPer
				+ ", septemberTotal=" + septemberTotal + ", septemberPer="
				+ septemberPer + ", octoberTotal=" + octoberTotal
				+ ", octoberPer=" + octoberPer + ", novemberTotal="
				+ novemberTotal + ", novemberPer=" + novemberPer
				+ ", decemberTotal=" + decemberTotal + ", decemberPer="
				+ decemberPer + ", yearTotal=" + yearTotal + ", yearPer="
				+ yearPer + "]";
	}

	
	
}
