import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthentmeansComponent } from './authentmeans.component';

describe('AuthentmeansComponent', () => {
  let component: AuthentmeansComponent;
  let fixture: ComponentFixture<AuthentmeansComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthentmeansComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthentmeansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
