package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class ResumeBean extends BaseBean {

    public Resume data;

    public Resume getData() {
        return data;
    }

    public void setData(Resume data) {
        this.data = data;
    }

    public static class Resume implements Serializable{
        private int id;
        private String createTime="";
        private String modifiedTime="";
        private String remarks="";
        private int enterpriseId;
        private String img="";
        private String currentResidence="";
        private String expectedCapital="";
        private String workPlace="";
        private String arrivalTime="";
        private int dType;
        private int status;
        private String enclosure="";
        private String introduce="";
        private String phone;
        private String wx;
        private String qq;
        private String mail;
        private List<PositionType> positionTypeList;
        private List<Industry> jobIndustryList;
        private Student studentVO;

        private List<Education> learningExperienceList;
        private List<Honor> inSchoolHonorList;
        private List<Position> schoolDutiesList;
        private List<Certificate> certificatesList;
        private String speciality;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getModifiedTime() {
            return modifiedTime;
        }

        public void setModifiedTime(String modifiedTime) {
            this.modifiedTime = modifiedTime;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(int enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCurrentResidence() {
            return currentResidence;
        }

        public void setCurrentResidence(String currentResidence) {
            this.currentResidence = currentResidence;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getExpectedCapital() {
            return expectedCapital;
        }

        public void setExpectedCapital(String expectedCapital) {
            this.expectedCapital = expectedCapital;
        }

        public String getWorkPlace() {
            return workPlace;
        }

        public void setWorkPlace(String workPlace) {
            this.workPlace = workPlace;
        }

        public String getArrivalTime() {
            return arrivalTime;
        }

        public void setArrivalTime(String arrivalTime) {
            this.arrivalTime = arrivalTime;
        }

        public int getdType() {
            return dType;
        }

        public void setdType(int dType) {
            this.dType = dType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getEnclosure() {
            return enclosure;
        }

        public void setEnclosure(String enclosure) {
            this.enclosure = enclosure;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public List<Education> getLearningExperienceList() {
            return learningExperienceList;
        }

        public void setLearningExperienceList(List<Education> learningExperienceList) {
            this.learningExperienceList = learningExperienceList;
        }

        public List<Honor> getInSchoolHonorList() {
            return inSchoolHonorList;
        }

        public void setInSchoolHonorList(List<Honor> inSchoolHonorList) {
            this.inSchoolHonorList = inSchoolHonorList;
        }

        public List<Position> getSchoolDutiesList() {
            return schoolDutiesList;
        }

        public void setSchoolDutiesList(List<Position> schoolDutiesList) {
            this.schoolDutiesList = schoolDutiesList;
        }

        public List<Certificate> getCertificatesList() {
            return certificatesList;
        }

        public void setCertificatesList(List<Certificate> certificatesList) {
            this.certificatesList = certificatesList;
        }

        public String getSpeciality() {
            return speciality;
        }

        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }

        public List<PositionType> getPositionTypeList() {
            return positionTypeList;
        }

        public void setPositionTypeList(List<PositionType> positionTypeList) {
            this.positionTypeList = positionTypeList;
        }

        public List<Industry> getJobIndustryList() {
            return jobIndustryList;
        }

        public void setJobIndustryList(List<Industry> jobIndustryList) {
            this.jobIndustryList = jobIndustryList;
        }

        public Student getStudentVO() {
            return studentVO;
        }

        public void setStudentVO(Student studentVO) {
            this.studentVO = studentVO;
        }

        public String getWx() {
            return wx;
        }

        public void setWx(String wx) {
            this.wx = wx;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }
    }


    public static class Student implements Serializable{
        private int id;
        private String name;
        private int education;
        private String sex;
        private String birthday;
        private String nation;
        private String nationality;
        private String address;
        private String imgUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getEducation() {
            return education;
        }

        public void setEducation(int education) {
            this.education = education;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }

    public static class Education implements Serializable{
        private int id;
        private int type;
        private int facultyid;
        private String faculty;
        private int majorid;
        private String major;
        private int education;
        private String grades="";
        private String admissiontime="";
        private int sid;
        private String schoolName="";
        private String region="";

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public int getFacultyid() {
            return facultyid;
        }

        public void setFacultyid(int facultyid) {
            this.facultyid = facultyid;
        }

        public int getMajorid() {
            return majorid;
        }

        public void setMajorid(int majorid) {
            this.majorid = majorid;
        }

        public int getEducation() {
            return education;
        }

        public void setEducation(int education) {
            this.education = education;
        }

        public String getGrades() {
            return grades;
        }

        public void setGrades(String grades) {
            this.grades = grades;
        }

        public String getAdmissiontime() {
            return admissiontime;
        }

        public void setAdmissiontime(String admissiontime) {
            this.admissiontime = admissiontime;
        }

        public String getFaculty() {
            return faculty;
        }

        public void setFaculty(String faculty) {
            this.faculty = faculty;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }
    }


    public static class Honor implements Serializable{
        private String acquisitionTime="";
        private String prize="";
        private String level="";

        public String getAcquisitionTime() {
            return acquisitionTime;
        }

        public void setAcquisitionTime(String acquisitionTime) {
            this.acquisitionTime = acquisitionTime;
        }

        public String getPrize() {
            return prize;
        }

        public void setPrize(String prize) {
            this.prize = prize;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }


    public static class Position implements Serializable{
        private String times="";
        private String name="";
        private String description="";

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


    public static class Certificate implements Serializable{
        private int id;
        private String acquisitionTime="";
        private String name="";
        private String remarks="";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAcquisitionTime() {
            return acquisitionTime;
        }

        public void setAcquisitionTime(String acquisitionTime) {
            this.acquisitionTime = acquisitionTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }


    public static class Speciality implements Serializable{
        private String level;
        private String name;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public static class PositionType implements Serializable{
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public static class Industry implements Serializable{
        private int id;
        private String industryTypeName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIndustryTypeName() {
            return industryTypeName;
        }

        public void setIndustryTypeName(String industryTypeName) {
            this.industryTypeName = industryTypeName;
        }
    }
}
