import {NgModule} from '@angular/core';
import {BrowserModule} from "@angular/platform-browser";
import {JntVTuberAppComponent} from "./jnt-vtuber-app.component";
import {JntVTuberAppRoutingModule} from "./jnt-vtuber-app-routing.module";

@NgModule({
  declarations: [
    JntVTuberAppComponent,
  ],
  imports: [
    JntVTuberAppRoutingModule,

    BrowserModule,
  ],
  providers: [],
  bootstrap: [
    JntVTuberAppComponent
  ]
})
export class JntVTuberAppModule {
}
