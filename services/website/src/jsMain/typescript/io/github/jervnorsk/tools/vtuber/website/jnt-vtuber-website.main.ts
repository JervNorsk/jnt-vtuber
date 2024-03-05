import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import {JntVTuberWebsiteModule} from "./jnt-v-tuber-website.module";

platformBrowserDynamic()
    .bootstrapModule(JntVTuberWebsiteModule)
    .catch(err => console.error(err));
