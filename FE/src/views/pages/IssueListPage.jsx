import React from "react";
import styled from "styled-components";
import { Input } from "antd";
import { SearchOutlined, CloseSquareOutlined } from "@ant-design/icons";
import Button from "@Common/Button";
import Dropdown from "@Common/Dropdown";
import Header from "@Common/Header";
import Label from "@Common/Label";
import NavButtons from "@Common/NavButtons";

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
            <NavButtons menus={["Labels", "Milestones"]} />
            <Button type="primary" text="New" />
          </IssueListTab>
          <ResetQueriesWrapper>
            <a>
              <CloseSquareOutlined style={{ marginRight: "5px" }} />
              Clear current search query, filters, and sorts
            </a>
          </ResetQueriesWrapper>
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

const ResetQueriesWrapper = styled.div``;

const IssueListWrapper = styled.div``;

const IssueListHeader = styled.div``;
const IssueStateWrapper = styled.div``;
const IssueFilterWrapper = styled.div``;

const IssueListBody = styled.div``;
