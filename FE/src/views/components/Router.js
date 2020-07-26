import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import IssueListPage from "Pages/IssueListPage";
import CreateIssuePage from "Pages/CreateIssuePage";
import IssueDetailPage from "Pages/IssueDetailPage";
import LabelListPage from "Pages/LabelListPage";

const LoginPage = () => <div>LoginPage</div>;
const MilestoneListPage = () => <div>MilestoneListPage</div>;
const CreateMilestonePage = () => <div>CreateMilestonePage</div>;
const EditMilestonePage = () => <div>EditMilestonePage</div>;
const NotFoundPage = () => <div>404</div>;

export default () => (
  <BrowserRouter>
    <Switch>
      <Route path="/" exact component={LoginPage} />
      <Route path="/issues" exact component={IssueListPage} />
      <Route path="/issues/new" exact component={CreateIssuePage} />
      <Route path="/issues/:id" component={IssueDetailPage} />
      <Route path="/milestones" component={MilestoneListPage} />
      <Route path="/milestones/new " component={CreateMilestonePage} />
      <Route path="/milestones/:id/edit" component={EditMilestonePage} />
      <Route path="/labels" component={LabelListPage} />
      <Route component={NotFoundPage} />
    </Switch>
  </BrowserRouter>
);
