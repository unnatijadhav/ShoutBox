import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportedshoutsComponent } from './reportedshouts.component';

describe('ReportedshoutsComponent', () => {
  let component: ReportedshoutsComponent;
  let fixture: ComponentFixture<ReportedshoutsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportedshoutsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportedshoutsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
