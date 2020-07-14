import React from "react";
import Button from "@Common/Button";
import Label from "@Common/Label";
import NavButtons from "@Common/NavButtons";
import Dropdown from "@Common/Dropdown";

const IssueListPage = () => {
  const onClickHandler = ({ key }) => {
    const [action, value] = key.split("_");
    console.log("action: ", action, "value: ", value);
  };
  const dropdownProps = {
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
    <div className="button-box">
      <Button type="primary" size="large" text="Sign in with Guthub" block />
      <Button type="primary" size="large" text="New" />
      <Button type="default" size="large" text="Cancel" />
      <Label text="FE" color="#f911e2" />
      <Button type="text" text="Edit" />
      <NavButtons menus={["Labels", "Milestones"]} />
      <Dropdown {...dropdownProps} />
    </div>
  );
};

export default IssueListPage;
