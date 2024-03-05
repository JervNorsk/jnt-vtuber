import {Component, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: '[jnt-vtuber-app]',
  templateUrl: './jnt-vtuber-app.component.html',
  styleUrls: ['./jnt-vtuber-app.component.sass']
})
export class JntVTuberAppComponent implements OnInit {

  @ViewChild("result")
  result!: HTMLImageElement

  @ViewChild("openImage")
  openImage!: HTMLButtonElement

  ngOnInit() {}

  doOpenImage(e: Event) {
    console.log("[doOpenImage]", e)
    debugger
  }
}
