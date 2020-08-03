import React, { useEffect } from "react";
import { Link, useHistory } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { getIssues } from "Store/issue/issueAction";
import styled from "styled-components";
import { Input, Checkbox } from "antd";
import {
  SearchOutlined,
  CloseSquareOutlined,
  ExclamationCircleOutlined,
  CheckOutlined,
  TagOutlined,
  FlagOutlined,
} from "@ant-design/icons";
import Button from "@/common/Button";
import Dropdown from "@/common/Dropdown";
import Header from "@/common/Header";
import NavButtons from "@/common/NavButtons";
import IssueItem from "@/issues/IssueItem";
import CustomizedDropdown from "@/common/Dropdown/Dropdown";

const IssueListPage = () => {
  const { error, issues } = useSelector((state) => state.issue);
  const { GET_ISSUES } = useSelector((state) => state.loading);
  const dispatch = useDispatch();
  const history = useHistory();

  useEffect(() => {
    const getIssuesFn = async () => {
      try {
        await getIssues()(dispatch);
      } catch (e) {
        console.log(e);
        dispatch(finishLoading("GET_ISSUES"));
      }
    };
    getIssuesFn();
  }, []);

  const onClickHandler = ({ key }) => {
    const [action, value] = key.split("_");
    console.log("action: ", action, "value: ", value);
  };

  const filterInputProps = {
    type: "button",
    title: "Filters",
    items: [
      { action: "action1", value: "Open issues" },
      { action: "action2", value: "Your issues" },
      { action: "action3", value: "Everything assigned to you" },
      { action: "action4", value: "Everything mentioning you" },
      { action: "action5", value: "Closed issues" },
    ],
    onClickHandler,
  };

  const authorData = {
    category: "users",
    title: "Author",
    openingCallback: null,
    closingCallback: null,
  };

  const LabelData = {
    category: "labels",
    title: "Labels",
    openingCallback: null,
    closingCallback: null,
  };

  const MilestoneData = {
    category: "milestones",
    title: "Milestone",
    openingCallback: null,
    closingCallback: null,
  };

  const AssigneeData = {
    category: "users",
    title: "Assignees",
    openingCallback: null,
    closingCallback: null,
  };

  return (
    <>
      <Header />
      <main>
        <div className="container">
          <IssueListTab>
            <IssueFilterInput>
              <Dropdown {...filterInputProps} />
              <Input
                prefix={<SearchOutlined />}
                defaultValue="is:open"
                style={{ flex: "1", minWidth: "120px", marginRight: "5px" }}
              />
            </IssueFilterInput>
            <NavButtons
              menus={[
                { icon: <TagOutlined />, title: "Labels", param: "/labels" },
                { icon: <FlagOutlined />, title: "Milestones", param: "/milestones" },
              ]}
            />
            <Button type="primary" text="New" onClick={() => history.push("/issues/new")} />
          </IssueListTab>
          <ResetQueriesWrapper>
            <a>
              <CloseSquareOutlined style={{ marginRight: "5px" }} />
              Clear current search query, filters, and sorts
            </a>
          </ResetQueriesWrapper>
          <IssueListWrapper>
            <IssueListHeader>
              <Checkbox />
              <IssueStateWrapper>
                <Button type="text" icon={<ExclamationCircleOutlined />} text="Open" />
                <Button type="text" icon={<CheckOutlined />} text="Closed" />
              </IssueStateWrapper>
              <IssueFilterWrapper>
                <CustomizedDropdown {...authorData} />
                <CustomizedDropdown {...LabelData} />
                <CustomizedDropdown {...MilestoneData} />
                <CustomizedDropdown {...AssigneeData} />
              </IssueFilterWrapper>
            </IssueListHeader>
            <IssueListBody>
              {GET_ISSUES && <h2>loading...</h2>}
              {error && <h2>error</h2>}
              {!GET_ISSUES &&
                issues.map((item) => (
                  <Link to={`/issues/${item.issueNumber}`} key={item.issueNumber}>
                    <IssueItem {...item} />
                  </Link>
                ))}
            </IssueListBody>
          </IssueListWrapper>
        </div>
      </main>
    </>
  );
};

export default IssueListPage;

const IssueListTab = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  .ant-radio-group {
    margin: 0 5px;
  }
  .ant-btn-primary {
    margin-left: 5px;
  }
  margin-bottom: 10px;
`;

const IssueFilterInput = styled.div`
  display: flex;
  min-width: 300px;
  flex: 1;
`;

const ResetQueriesWrapper = styled.div`
  margin-bottom: 20px;
`;

const IssueListWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 1px solid #e1e4e8;
  border-radius: 5px;
`;

const IssueListHeader = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  padding: 12px;
  background: #fafbfc;
  width: 100%;
  border-radius: 5px 5px 0 0;
  border-bottom: none;
`;

const IssueStateWrapper = styled.div`
  display: flex;
  width: fit-content;
  margin-left: 10px;
  margin-right: auto;
`;

const IssueFilterWrapper = styled.div`
  display: flex;
`;

const IssueListBody = styled.div`
  width: 100%;
`;
