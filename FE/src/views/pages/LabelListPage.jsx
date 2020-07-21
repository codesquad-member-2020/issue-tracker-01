import React, { useState } from "react";
import styled from "styled-components";
import Header from "@/common/Header";
import NavButtons from "@/common/NavButtons";
import Button from "@/common/Button";
import LabelEditor from "@/labels/LabelEditor";
import { TagOutlined, FlagOutlined } from "@ant-design/icons";
import { list } from "Assets/mockLabel";

const LabelListPage = () => {
  const [creating, setCreating] = useState(false);

  const handlersForCreateMode = {
    onClickCancel: setCreating,
    onClickConfirm: null,
  };

  const handlersForListMode = {
    onClickDelete: null,
    onClickConfirm: null,
  };

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
            <Button type="primary" text="New label" onClick={() => setCreating(!creating)} />
          </Nav>
          {creating && <LabelEditor mode="create" handlers={handlersForCreateMode} />}
          <LabelListWrapper>
            <LabelListHeader>2 labels</LabelListHeader>
            <LabelListBody>
              {list.map((item) => (
                <LabelEditor
                  key={item.id}
                  mode="list"
                  values={item}
                  handlers={handlersForListMode}
                />
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
  margin-top: 16px;
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
