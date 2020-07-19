import React from "react";
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
import CustomizedDropdown from "@/common/CustomizedDropdown";
import { list } from "Assets/mockIssue";

const IssueListPage = () => {
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
    title: "Author",
    itemList: ["reesekimm", "dion", "alex"],
    onSelect: null,
  };

  const LabelData = {
    title: "Label",
    itemList: ["FE", "BE"],
    onSelect: null,
  };
  const MilestoneData = {
    title: "Milestones",
    itemList: ["Phase1", "Phase2"],
    onSelect: null,
  };
  const AssigneeData = {
    title: "Assignee",
    itemList: ["reesekimm", "alex"],
    onSelect: null,
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
            <Button type="primary" text="New" />
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
              {list.map((item) => (
                <IssueItem key={item.issueNumber} {...item} />
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
