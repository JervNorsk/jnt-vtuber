import {NgModule} from '@angular/core';
import {BrowserModule} from "@angular/platform-browser";
import {JntVTuberWebsiteComponent} from "./jnt-vtuber-website.component";
import {JntVTuberAppRoutingModule} from "./jnt-vtuber-website-routing.module";

@NgModule({
  declarations: [
    JntVTuberWebsiteComponent,
  ],
  imports: [
    JntVTuberAppRoutingModule,

    BrowserModule,
  ],
  providers: [],
  bootstrap: [
    JntVTuberWebsiteComponent
  ]
})
export class JntVTuberWebsiteModule {
}
