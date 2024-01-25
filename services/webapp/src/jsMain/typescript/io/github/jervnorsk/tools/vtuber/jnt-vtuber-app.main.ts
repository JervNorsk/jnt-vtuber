import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import {JntVTuberAppModule} from "./jnt-vtuber-app.module";

platformBrowserDynamic()
    .bootstrapModule(JntVTuberAppModule)
    .catch(err => console.error(err));
