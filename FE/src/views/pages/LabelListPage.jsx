import React from "react";
import styled from "styled-components";
import Header from "@/common/Header";
import NavButtons from "@/common/NavButtons";
import Button from "@/common/Button";
import LabelItem from "@/labels/LabelItem";
import { TagOutlined, FlagOutlined } from "@ant-design/icons";
import { list } from "Assets/mockLabel";

const LabelListPage = () => {
  return (
    <>
      <Header />
      <main>
        <div className="container">
          <Nav>
            <NavButtons
              menus={[
                { icon: <TagOutlined />, title: "Labels", param: "/labels" },
                { icon: <FlagOutlined />, title: "Milestones", param: "/milestones" },
              ]}
            />
            <Button type="primary" text="New label" />
          </Nav>
          <LabelListWrapper>
            <LabelListHeader>2 labels</LabelListHeader>
            <LabelListBody>
              {list.map((item) => (
                <LabelItem key={item.id} {...item} />
              ))}
            </LabelListBody>
          </LabelListWrapper>
        </div>
      </main>
    </>
  );
};

export default LabelListPage;

const Nav = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 16px;
`;

const LabelListWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 1px solid #e1e4e8;
  border-radius: 5px;
`;

const LabelListHeader = styled.div`
  padding: 16px 12px;
  background: #fafbfc;
  width: 100%;
  border-radius: 5px 5px 0 0;
  border-bottom: none;
  font-weight: bold;
`;

const LabelListBody = styled.div`
  width: 100%;
`;
