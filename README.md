# 목차

### [1. 계획 수립](#1-계획-수립)
#### [1-1. 프로젝트 발단](#1-1-프로젝트-발단)
#### [1-2. 사용 기술](#1-2-사용-기술)
#### [1-3. 팀 구성 및 역할](#1-3-팀-구성-및-역할)
#### [1-4. 아키텍처](#1-4-아키텍처)
[1-4-1. Private Cloud(vSphere) & Public Cloud(AWS)](#1-4-1-private-cloudvsphere--public-cloudaws)
### [2. 진행 상황](#2-진행-상황)
#### [2-1. 진행 계획 관리](#2-1-진행-계획-관리)
#### [2-2. 간트 차트](#2-2-간트-차트)
### [3. 구성 과정](#3-구성-과정)


</br>
</br>

# 국립생태원 웹 서비스 인프라 강화

## 1. 계획 수립

### 1-1. 프로젝트 발단

|**서비스**|생태 체험 프로그램|
| :-: | - |
|**참조**|<p>Github: https://github.com/IDontHaveBrain/project04</p><p>국립생태원: https://www.nie.re.kr/reserve/main/mainPage.do;jsessionid=63B8B8F8B07EFDD12B32EB9A24198799.nie\_was</p>|
|**발단**|근래 급격한 트래픽 증가로 인한 원활한 서비스 제공 환경 요구|
|**목적**|고가용성, 내결함성을 만족하는 확장 가능한 유연한 서비스 제공|
|**기대효과**|<p>1\. 일관되고 예상가능한 시스템 운영으로 업무 효율성 증가</p><p>2\. 안전하고 빠른 서비스를 제공할 수 있는 환경</p><p>3\. 실시간 모니터링, 신속한 대응으로 서비스 성능 유지</p>|

### 1-2. 사용 기술

<table><tr><th rowspan="2"><b>안정성</b></th><th>IaC</th><th valign="top">Terraform, Ansible</th></tr>
<tr><td>CI/CD</td><td valign="top">Github, ArgoCD</td></tr>
<tr><td rowspan="2"><b>가용성</b></td><td>DB 복제본</td><td valign="top">RDS</td></tr>
<tr><td>Session Clustering</td><td valign="top">ElastiCache for Redis</td></tr>
<tr><td><b>확장성</b></td><td valign="top">Auto Scaling</td><td valign="top">RDS</td></tr>
<tr><td rowspan="2"><b>장애조치</b></td><td valign="top">Monitoring</td><td valign="top">WhaTap, CloudWatch, SNS</td></tr>
<tr><td valign="top">Log Backup</td><td valign="top">EventBridge, SNS, SQS, Lambda, S3</td></tr>
</table>

### 1-3. 팀 구성 및 역할

<table><tr><th valign="top"></th><th valign="top"><b>이름</b></th><th valign="top"><b>역할</b></th></tr>
<tr><td rowspan="2"><p><b>Private Cloud</b></p><p><b>(vSphere)</b></p></td><td>김현빈(PM)</td><td valign="top"><p>Vsphere 인프라 환경 구성</p><p>Openshift 내부 구성</p><p>아키텍쳐 구성</p></td></tr>
<tr><td>정동호</td><td valign="top"><p>Vsphere 인프라 환경 구성</p><p>Openshift 내부 구성</p><p>Whatap을 활용한 모니터링 구성</p></td></tr>
<tr><td rowspan="2"><p><b>Public Cloud</b></p><p><b>(AWS)</b></p></td><td>박민하</td><td valign="top"><p>AWS 인프라 환경 구성</p><p>Terraform을 통한 AWS 구성</p><p>ppt 및 보고서 작성</p></td></tr>
<tr><td>최형서</td><td valign="top"><p>AWS 인프라 환경 구성</p><p>Terraform을 통한 AWS 구성</p></td></tr>
</table>

### 1-4. 아키텍처

#### 1-4-1. Private Cloud(vSphere) & Public Cloud(AWS)
![](https://velog.velcdn.com/images/miracle-21/post/3ca478bc-bb33-47cf-8fd6-7071681cd61e/image.png)



## 2. 진행 상황

### 2-1. 진행 계획 관리

|**사용 툴**|노션(Notion)|
| :-: | - |
|**URL**|https://respected-jingle-97e.notion.site/Hybrid-Final-4Team-c4722c8b80f149cb9237ee4c24a9e050?pvs=4|
|**분류**|<p>1. 회의록</p><p>- 매일 진행 상황 공유</p><p>2. 진행계획 및 역할분담</p><p>- 팀원 별로 업무 분담</p><p>- 완료된 작업은 체크하여 프로젝트 완성도 확인</p><p>3. Private Cloud</p><p>- 세부적인 vSphere 진행 사항 기록</p><p>4. Public Cloud</p><p>- 세부적인 AWS 진행 사항 기록</p><p>5. 참고 자료</p><p>- 작업 중 참고한 사이트 URL 공유</p><p>6. 산출물</p><p>- 작업에 필요한 파일 공유</p>|


### 2-2. 간트 차트
![](https://velog.velcdn.com/images/miracle-21/post/07da3cbd-73b6-4f53-ac90-535ea7b37b52/image.png)


## 3. 구성 과정
세부 내용은 [보고서.docx](https://github.com/miracle-21/hybrid-final/blob/main/%5Bhybrid-final%5D%EB%B3%B4%EA%B3%A0%EC%84%9C.docx) 참조

### 3-1. Private Cloud(vSphere)
#### 3-1-1. Domain Controller 설치
#### 3-1-2. Domain Controller 서버 구성
#### 3-1-3. VCenter 설치
#### 3-1-4. Ansible을 이용한 가상 스위치 및 VM 설치 자동화
#### 3-1-5. Bastion 서버에서 OpenShift 설치
#### 3-1-6. 웹 서비스 배포(NFS, ArgoCD, LoadBalancer)
#### 3-1-7. 외부접속을 위한 Port Forwarding
#### 3-1-8. 파드 부하테스트(Jmeter) HPA Autoscaling
#### 3-1-9. WhaTap 모니터링
### 3-2. Public Cloud(AWS)
#### 3-2-1. Terraform를 이용한 EKS 3tier 구축 자동화
#### 3-2-2. 파드 오토스케일링 및 부하 테스트(Jmeter)
#### 3-2-3. EKS 모니터링을 위한 Container Insights
#### 3-2-4. CloudWatch 로그 자동 백업
#### 3-2-5. CPU와 메모리 사용량에 따른 SNS 알림
#### 3-2-6. ElastiCache for Redis를 이용한 세션 관리
#### 3-2-7. EKS Ingress 서비스에서 CloudFront 연결
