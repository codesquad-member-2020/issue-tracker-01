import React from "react";
import Button from "@Common/Button";
import NavButtons from "@Common/NavButtons";

const IssueListPage = () => {
  return (
    <div className="button-box">
      <Button type="primary" size="large" text="Sign in with Guthub" block />
      <Button type="primary" size="large" text="New" />
      <Button type="default" size="large" text="Cancel" />
      <Button type="default" shape="round" text="FE" bgcolor="#f911e2" />
      <Button type="text" text="Edit" />
      <NavButtons menus={["Labels", "Milestones"]} />
    </div>
  );
};

export default IssueListPage;
