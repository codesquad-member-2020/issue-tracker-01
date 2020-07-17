import React from "react";
import styled from "styled-components";
import Header from "@/common/Header";
import NavButtons from "@/common/NavButtons";
import Button from "@/common/Button";
import LabelItem from "@/labels/LabelItem";
import LabelEditor from "@/labels/LabelEditor";
import Label from "@/common/Label";
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
          <LabelMaker>
            <PreviewWrapper>
              <Label title="Label preview" color="#7057FF" />
            </PreviewWrapper>
            <LabelEditor mode="create" />
          </LabelMaker>
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

const LabelMaker = styled.div`
  width: 100%;
  background: #fafbfc;
  border-radius: 5px;
  border: 1px solid #e1e4e8;
  margin-bottom: 16px;
`;

const PreviewWrapper = styled.div`
  width: 100%;
  padding: 20px;
`;

const LabelListWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 1px solid #e1e4e8;
  border-radius: 5px;
`;

const LabelListHeader = styled.div`
  padding: 20px;
  background: #fafbfc;
  width: 100%;
  border-radius: 5px 5px 0 0;
  border-bottom: none;
  font-weight: bold;
`;

const LabelListBody = styled.div`
  width: 100%;
`;
