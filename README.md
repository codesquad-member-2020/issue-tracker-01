# issue-tracker-01
이슈 트래커 - 1팀![GitHub stars](https://img.shields.io/github/stars/codesquad-member-2020/issue-tracker-01?style=social)

![GitHub language count](https://img.shields.io/github/languages/count/codesquad-member-2020/issue-tracker-01)![GitHub pull requests](https://img.shields.io/github/issues-pr/codesquad-member-2020/issue-tracker-01?color=green)![GitHub issues](https://img.shields.io/github/issues/codesquad-member-2020/issue-tracker-01)![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/codesquad-member-2020/issue-tracker-01/1)![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/codesquad-member-2020/issue-tracker-01/2)![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/codesquad-member-2020/issue-tracker-01/3)

### BE

![GitHub issues by-label](https://img.shields.io/github/issues/codesquad-member-2020/issue-tracker-01/🚌 BE)![GitHub pull requests by-label](https://img.shields.io/github/issues-pr/codesquad-member-2020/issue-tracker-01/🚌 BE?color=green)

### FE

![GitHub issues by-label](https://img.shields.io/github/issues/codesquad-member-2020/issue-tracker-01/🦄 FE)![GitHub pull requests by-label](https://img.shields.io/github/issues-pr/codesquad-member-2020/issue-tracker-01/🦄 FE?color=green)

## Member

| Class | Nickname                       |
| ----- | ------------------------------ |
| BE    | [🚌 Alex][alex], [🐱 Dion][dion] |
| FE    | [🦄 Reese][reese]               |

### [Wiki](https://github.com/codesquad-member-2020/issue-tracker-01/wiki)

### [API Document](https://github.com/codesquad-member-2020/issue-tracker-01/wiki/API-Document)

- 추후 API 서버에서도 제공할 예정

### [Scrum](https://github.com/codesquad-member-2020/issue-tracker-01/wiki/Scrum)

### How To Bulid

#### BE

Java Version 1.8+

In project directory, `gradlew build`

and then`java -jar build/libs/1.0.0-Octocat.jar`

or background deploy `nohup java -jar build/lib/1.0.0-Octocat.jar &`

#### FE

Version

## 프로젝트 관리(Project Management)

### 개발 환경 관리(Environment Management)

- 각 Class 별 별도 디렉토리에 소스코드를 위치시킨다.(BE/FE) Sub-Module은 사용하지 않는다.

### 프로젝트 진행상황 관리(Project Progress Management)

- Label을 달아서 해당 Issue 혹은 PR이 어떤 상태인지 명시적으로 드러나도록 한다.

- MileStone을 Phase 별로 두어서 주차별로 무엇을 해야할지, 무엇을 했는지 드러나도록 한다.

  - 각 주차별로 Phase-{weeknum}을 붙여서 Milestone을 정의한다.
  - 주의 시작에 간단하게 Milestone을 정의한다.

- BE, Team의 경우 Github의 Project Kanban을 활용, 완료된 항목과 진행중인 항목이 눈에 띌 수 있도록 한다.
  Phase 별로 구분할 수 있도록 한다.

### 요구사항 관리(Requirement Management)

### [FE Task List](https://docs.google.com/spreadsheets/d/1U-_ApHx159JGWFy9P9KViVSK9zQEAFZmE7JAQ2qmW0c/edit?usp=sharing)

### [C4 Model](https://github.com/codesquad-member-2020/issue-tracker-01/issues/16)

- Github Issue를 통해 task list에서 구체화된 Issue를 생성하여 개발 요구사항을 관리한다.
- Issue 단위로 개발하되 Issue는 sub task를 가질 수 있다.

### 브랜치 & 커밋 관리(Branch & Commit Management)

- Branch의 이름과 Commit의 이름은 항상 GitHub의 이슈번호로 시작합니다.
- FE(Front End) / BE(Back End)

#### 브랜치 관리(Branch Management)

- Branch: feature/[ClassName]-#{Issue No.}

  > e.g. feature/BE-#1

---

- master: 배포용 브랜치

- dev: 개발 브랜치(Default)

- 이슈 단위로 브랜치를 생성하여 개발한다.

- 개발 완료 후, 작업하던 브랜치에서 각자 환경의 개발 브랜치(dev-[ClassName]-phase[weeknum])로 PR를 생성한다.

- BE는 PR시 상대방은 reviewer에 할당하고, 할당받은 사람은 해당 PR을 확인 후, merge 한다.

- dev 브랜치로 merge 될 때, PR Body에서 해당하는 issue를 닫아준다.

  > e.g. Resolve #1, Closed #1 ...

- review를 위해 리뷰 브랜치를 생성한다.

  > e.g. FE-review-phase1

- 각자 클래스별 기능 개발 브랜치를 따로 두어서 리뷰어의 리뷰 를 잘 받는다.

  > e.g. dev-FE-phase1

#### 커밋 관리(Commit Management)

- Commit: [ClassName]-#{Issue No.} type: 커밋내용

  > e.g. BE-#1 feat: 새로운 기능 추가

### PR 관리(Pull request Management)

#### ✅ PR Template

```
Fixes # (issue)

## 설명

변경사항이나 고쳐진 이슈에 대한 요약을 포함해주세요.
왜 고쳤는지에 대한 정보를 포함해주세요.
이 변경사항에 필요한 의존성 목록을 작성해주세요.

## 작업 유형

- [ ] 신규 기능 추가
- [ ] 버그 수정
- [ ] 관련 문서 업데이트 필요

## 체크리스트

- [ ] 이 PR의 코드들이 이 프로젝트의 스타일 가이드를 준수했는가?
- [ ] 내 코드에 대한 자기 검토가 되었는가?
- [ ] 이해하기 힘든 부분의 코드에 주석이 작성되었는가?
- [ ] 변경사항에 대한 문서를 작성하였는가?
- [ ] 변경사항이 새로운 경고를 만들어내지 않았는가?
- [ ] 변경사항이 효과적이거나 동작이 작동한다는 것을 보증하는 테스트를 추가하였는가?
- [ ] 새로운 테스트와 기존의 테스트가 변경사항에 대해 만족하는가?
```

### 우리는 어떻게 커뮤니케이션 하는가?

코로나 사태에 대응하여 온라인 소통매체인 Slack, HackMD, Google Meet을 활용한다.

Github의 Communication 기능을 활용한다.

대면할 때에도 마스크를 착용하는 것을 권장한다.

[alex]: https://github.com/haveagood
[reese]: https://github.com/reesekimm
[dion]: https://github.com/ksundong

