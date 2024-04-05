import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { StatusPipe } from './pipes/status.pipe';
import {CurrencyPipe} from "@angular/common";


@NgModule({
    declarations: [
        StatusPipe
    ],
    imports: [
        ReactiveFormsModule,
        FormsModule,
        CurrencyPipe,
    ],
    exports: [
        FormsModule,
        StatusPipe,
        ReactiveFormsModule
    ]
})
export class SharedModule { }
