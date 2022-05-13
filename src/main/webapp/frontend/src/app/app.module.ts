import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {Ng2SearchPipeModule} from 'ng2-search-filter';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule, routesList} from './app-routing.module';
import {AppComponent} from './app.component';
import {MainComponent} from './layouts/main/main.component';
import {NavbarComponent} from './layouts/navbar/navbar.component';
import {ImageComponent} from './image/image.component';
import {EditimageComponent} from './image/edit/editimage.component';
import {DeleteImageComponent} from './image/delete/delete-image.component';
import {RuleComponent} from './rule/rule.component';
import {RuleDeleteComponent} from './rule/rule-delete/rule-delete.component';
import { RuleEditComponent } from './rule/rule-edit/rule-edit.component';


@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    NavbarComponent,
    routesList,
    ImageComponent,
    EditimageComponent,
    DeleteImageComponent,
    RuleComponent,
    RuleDeleteComponent,
    RuleEditComponent

  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgbModule,
    Ng2SearchPipeModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
