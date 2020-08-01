import React from "react";
import styled from "styled-components";
import { Avatar } from "antd";
import { UserOutlined } from "@ant-design/icons";

const User = ({ nickname, profileImage, size }) => {
  return (
    <Wrapper>
      <Avatar
        src={profileImage}
        icon={profileImage ? "" : <UserOutlined />}
        size={size}
        style={{ marginRight: "5px" }}
      />{" "}
      {nickname}
    </Wrapper>
  );
};

export default User;

const Wrapper = styled.div`
  :not(:last-child) {
    padding-bottom: 5px;
  }
`;
